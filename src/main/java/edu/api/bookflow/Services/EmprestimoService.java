package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.Mapper.EmprestimoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Repository.EmprestimoRepository;
import edu.api.bookflow.Services.patchHttpRequest.GlobalPatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Validated
@Service
public class EmprestimoService {



    @Autowired
    private static EmprestimoRepository emprestimoRepository;
    @Autowired
    private static EmprestimoMapper emprestimoMapper;
    @Autowired
    private static LivroService livroService;

    @Autowired
    private static AlunoService alunoService;
    @Autowired
    private GlobalPatch patcher;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
    }

    /*public PaginationDTO<EmprestimoDTO> listAll(@RequestParam(name = "pag") @PositiveOrZero int pageNumber,
                                                @RequestParam(name = "size") @Positive @Max(50) int pageSize,
                                                @RequestParam(value = "sortBy", defaultValue = "data_emp") String sortBy,
                                                @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Emprestimo> page = emprestimoRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
        List<EmprestimoDTO> list = page.stream().map(emprestimoMapper::convertToDto).toList();

        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }*/

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

    public EmprestimoDTO create(@Valid EmprestimoDTO dto) {
        validaAluno(dto);
        validaLivro(dto);
        return emprestimoMapper.convertToDto(emprestimoRepository.save(emprestimoMapper.convertToEntity(dto)));
    }

    public EmprestimoDTO update(@Positive Long id, @Valid EmprestimoDTO dto) {
        return emprestimoRepository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return emprestimoMapper.convertToDto(emprestimoRepository.save(registroEncontrado));
                }).orElseThrow(() -> new NotFoundObject(id));
    }

    public EmprestimoDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        patcher.globalPatch(fields, emprestimoExistente);
        //Livro livro = livroService.finById();
        return emprestimoMapper.convertToDto(this.emprestimoRepository.save(emprestimoExistente));
    }

    public void delete(@Positive Long id) {
        emprestimoRepository.delete(emprestimoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    /*
     * Metodo utilizado para validar se o aluno já possui um empréstimo ativo
     * @param EmprestimoDTO emprestimoDTO
     * */
    private static void validaAluno(EmprestimoDTO emprestimoDTO) {
        Long idAluno = emprestimoDTO.codAluno().getCodAluno();
        LocalDate hoje = LocalDate.now();

        // Busca todos os empréstimos do aluno no banco de dados
        List<Emprestimo> emprestimosDoAluno = emprestimoRepository.findByCodAluno_CodAluno(idAluno);

        for (Emprestimo emprestimo : emprestimosDoAluno) {
            // Verifica se o empréstimo não foi devolvido
            if (emprestimo.getFoiDevolvido() == false) {
                // Verifica se o empréstimo está atrasado
                if (emprestimo.getDataPrevistaDevolucao().isBefore(hoje)) {
                    throw new IllegalStateException("O(A) aluno(a) " + emprestimo.getCodAluno().getNomeCompleto() + " possui um empréstimo atrasado! Data de entrega estava prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                } else {
                    throw new IllegalStateException("O(A) aluno(a) " + emprestimo.getCodAluno().getNomeCompleto() + " já possui um empréstimo ativo com data de entrega prevista para " + emprestimo.getDataPrevistaDevolucao() + ".");
                }
            }
        }
    }

    /*
     * Metodo utilizado para validar se o livro já está em um emréstimo ativo
     * @param EmprestimoDTO emprestimoDTO
     * */
    private static void validaLivro(EmprestimoDTO emprestimoDTO) {
        Long idLivro = emprestimoDTO.codLivro().getCodLivro();
        LocalDate hoje = LocalDate.now();
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
