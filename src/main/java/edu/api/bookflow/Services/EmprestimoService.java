package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.EmprestimoDevolucaoDTO;
import edu.api.bookflow.DTO.Mapper.EmprestimoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.Usuario;
import edu.api.bookflow.Repository.AlunoRepository;
import edu.api.bookflow.Repository.EmprestimoRepository;
import edu.api.bookflow.Repository.LivroRepository;
import edu.api.bookflow.Repository.UsuarioRepository;
import edu.api.bookflow.Services.patchHttpRequest.GlobalPatch;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Service
public class EmprestimoService {


    @Autowired
    private final EmprestimoRepository emprestimoRepository;
    @Autowired
    private final EmprestimoMapper emprestimoMapper;
    @Autowired
    private final AlunoRepository alunoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    LivroRepository livroRepository;
    @Autowired
    private GlobalPatch patcher;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper, AlunoRepository alunoRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.alunoRepository = alunoRepository;
    }

    public PaginationDTO<EmprestimoDTO> listAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                                @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                                @RequestParam(value = "sortBy", defaultValue = "dataEmprestimo") String sortBy,
                                                @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
                                                @RequestParam(name = "cancelado", defaultValue = "false") Boolean cancelado,
                                                @RequestParam(name = "foiDevolvido", defaultValue = "false") Boolean foiDevolvido,
                                                @RequestParam(name = "atrasado", defaultValue = "false") Boolean atrasado) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Emprestimo> page = emprestimoRepository.findAllByCanceladoAndAtrasado(cancelado, foiDevolvido, atrasado, PageRequest.of(pageNumber, pageSize, sort));
        List<EmprestimoDTO> list = page.stream().map(emprestimoMapper::convertToDto).toList();

        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public EmprestimoDTO findById(@Positive @NotNull Long id) {
        return emprestimoRepository.findById(id).map(emprestimoMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    @Transactional
    public EmprestimoDTO create(EmprestimoDTO dto) {
        // Converter DTO para entidade
        Emprestimo emprestimo = emprestimoMapper.convertToEntity(dto);

        Livro livro = validaLivro(dto);

        // Setar entidades relacionadas
        emprestimo.setRespEmprestimo(validaResponsávelEmpréstimo(dto));
        emprestimo.setCodAluno(validaAluno(dto));
        emprestimo.setCodLivro(livro);
        livro.setSttsEmprestado(true);
        livroRepository.save(livro);

        // Salvar no banco de dados
        Emprestimo salvo = emprestimoRepository.save(emprestimo);

        // Converter entidade salva para DTO e retornar
        return emprestimoMapper.convertToDto(salvo);
    }

    @Transactional
    public EmprestimoDTO update(@Positive Long id, @Valid EmprestimoDTO dto) {
        return emprestimoRepository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return emprestimoMapper.convertToDto(emprestimoRepository.save(registroEncontrado));
                }).orElseThrow(() -> new NotFoundObject(id));
    }

    @Transactional
    public EmprestimoDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        patcher.globalPatch(fields, emprestimoExistente);
        return emprestimoMapper.convertToDto(this.emprestimoRepository.save(emprestimoExistente));
    }

    public void delete(@Positive Long id) {
        emprestimoRepository.delete(emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> cancelaEmprestimo(@Positive Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        Livro livro = emprestimo.getCodLivro();

        if (!emprestimo.getCancelado()) { //se falso
            emprestimo.setCancelado(true);
            emprestimo.setFoiDevolvido(true);
            livro.setSttsEmprestado(false);
            emprestimoRepository.save(emprestimo);
            livroRepository.save(livro);

            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Empréstimo cancelado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "");
        }
    }

    public ResponseEntity<Object> devolverEmprestimo(@Positive Long id, @Valid EmprestimoDevolucaoDTO devolucaoDTO){
        // busca o empréstimo pelo ID
        Emprestimo emprestimo = emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (emprestimo.getFoiDevolvido()){
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "");
        }
        // valida e busca o responsável pela devolução
        Usuario respDevolucao = validaResponsávelDevolucao(devolucaoDTO);

        // busca o livro associado ao empréstimo
        Livro livro = emprestimo.getCodLivro();

        // atualiza as informações do empréstimo
        String observacao = devolucaoDTO.observacao();
        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucao(hoje);
        emprestimo.setFoiDevolvido(true);
        emprestimo.setRespDevolucao(respDevolucao);
        emprestimo.setObservacao(observacao);

        // atualiza o status do livro para disponível
        livro.setSttsEmprestado(false);
        livroRepository.save(livro);

        // salva o empréstimo atualizado
        return ApiHttpResponse.responseStatus(HttpStatus.OK, "Empréstimo devolvido com sucesso!");
    }

    /*
     * Metodo utilizado para validar se o aluno já possui um empréstimo ativo
     * @param EmprestimoDTO emprestimoDTO
     * */
    private Aluno validaAluno(EmprestimoDTO emprestimoDTO) {
        Long idAluno = emprestimoDTO.aluno().codAluno();
        LocalDate hoje = LocalDate.now();
        Aluno aluno = alunoRepository.findById(idAluno).orElseThrow(() -> new NotFoundObject(idAluno));

        if (!aluno.getStatus()) {
            throw new IllegalStateException("O aluno " + aluno.getNomeCompleto() + " está desativado e não pode solicitar empréstimos.");
        }

        // Busca todos os empréstimos do aluno no banco de dados
        List<Emprestimo> emprestimosDoAluno = emprestimoRepository.findByCodAluno_CodAluno(idAluno);

        for (Emprestimo emprestimo : emprestimosDoAluno) {
            // Verifica se o empréstimo não foi devolvido
            if (emprestimo.getFoiDevolvido() == false) {
                // Verifica se o empréstimo está atrasado
                if (emprestimo.getDataPrevistaDevolucao().isBefore(hoje)) {
                    throw new IllegalStateException("O(A) aluno(a) " + emprestimo.getCodAluno().getNomeCompleto() + " possui um empréstimo atrasado! Data de entrega estava prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                } else {
                    throw new IllegalStateException("O(A) aluno(a) " + emprestimo.getCodAluno().getNomeCompleto() + " possui um empréstimo ativo com data de entrega prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                }
            }
        }

        return aluno;
    }

    /*
     * Metodo utilizado para validar se o livro já está em um emréstimo ativo
     * @param EmprestimoDTO emprestimoDTO
     * */
    private Livro validaLivro(EmprestimoDTO emprestimoDTO) {
        Long idLivro = emprestimoDTO.codLivro().getCodLivro();
        LocalDate hoje = LocalDate.now();

        Livro livro = livroRepository.findById(idLivro).orElseThrow(()-> new NotFoundObject(idLivro));

        if (!livro.getStatus()){
            throw new IllegalStateException("O livro " + livro.getPatrimonio() + " está desativado e não pode solicitado para empréstimos.");
        }

        List<Emprestimo> emprestimosDoLivro = emprestimoRepository.findByCodLivro_CodLivro(idLivro);

        for (Emprestimo emprestimo : emprestimosDoLivro) {
            // Verifica se o empréstimo está atrasado
            if (emprestimo.getFoiDevolvido() == false) {
                if (emprestimo.getDataPrevistaDevolucao().isBefore(hoje)) {
                    throw new IllegalStateException("O livro de ID " + idLivro + " está vinculado a outro epréstimo em atraso! Data de entrega estava prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                } else {
                    throw new IllegalStateException("O livro de ID " + idLivro + " já está emprestado! Data de entrega prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                }
            }
        }

        return  livro;
    }

    protected boolean findAlunoByAlunoAndData(Aluno aluno) {
        boolean temEmprestimo = emprestimoRepository.existsByCodAlunoAndFoiDevolvidoFalseAndCanceladoFalse(aluno);
        return temEmprestimo;
    }

    private Usuario validaResponsávelEmpréstimo(EmprestimoDTO emprestimoDTO) {
        Long idUsuario = emprestimoDTO.respEmprestimo().codUsuario();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFoundObject(idUsuario));

        if (!usuario.getStatus()) {
            throw new IllegalStateException("O usuário " + usuario.getNome() + " está desativado e não pode realizar empréstimos.");
        }
        return usuario;
    }

    private Usuario validaResponsávelDevolucao(EmprestimoDevolucaoDTO emprestimoDTO) {
        Long idUsuario = emprestimoDTO.respDevolucao().codUsuario();
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFoundObject(idUsuario));

        if (!usuario.getStatus()) {
            throw new IllegalStateException("O usuário " + usuario.getNome() + " está desativado e não pode realizar a devolução de empréstimos.");
        }
        return usuario;
    }

    /*
     * Metodo programado para ser executado todo dia à meia noite
     * */
    @Scheduled(cron = "0 0 0 * * *")
    public void validaAtraso() {
        LocalDate hoje = LocalDate.now();
        List<Emprestimo> emprestimoList = emprestimoRepository.findAll();

        for (Emprestimo emprestimo : emprestimoList) {
            if (emprestimo.getDataPrevistaDevolucao().isBefore(hoje) && emprestimo.getFoiDevolvido() == false) {
                emprestimo.setAtrasado(true);
            }
            emprestimoRepository.save(emprestimo);
        }

    }


}
