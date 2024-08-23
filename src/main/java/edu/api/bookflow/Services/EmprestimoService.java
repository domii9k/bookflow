package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.DTO.Mapper.EmprestimoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Repository.EmprestimoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Validated
@Service
public class EmprestimoService {


    private final EmprestimoRepository emprestimoRepository;
    private final EmprestimoMapper emprestimoMapper;
    @Autowired
    private final LivroService livroService;
    @Autowired
    private final AlunoService alunoService;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, EmprestimoMapper emprestimoMapper, LivroService livroService, AlunoService alunoService) {
        this.emprestimoRepository = emprestimoRepository;
        this.emprestimoMapper = emprestimoMapper;
        this.livroService = livroService;
        this.alunoService = alunoService;
    }

    public PaginationDTO<EmprestimoDTO> listAll (@RequestParam(name = "pag")@PositiveOrZero int pageNumber,
                                              @RequestParam(name = "size") @Positive @Max(50) int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "data_emp") String sortBy,
                                              @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir)  {
        // Verifica se o valor de
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Emprestimo> page = emprestimoRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
        List<EmprestimoDTO> list = page.stream().map(emprestimoMapper::convertToDto).toList();

        return  new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public EmprestimoDTO findById(@Positive @NotNull Long id){
        return emprestimoRepository.findById(id).map(emprestimoMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public EmprestimoDTO create(@Valid EmprestimoDTO dto){
        validaAlunoOnCreateEmprestimo(dto);
        livroService.validaLivroOnCreateEmprestimo(dto);
        return emprestimoMapper.convertToDto(emprestimoRepository.save(emprestimoMapper.convertToEntity(dto)));
    }

    private void validaAlunoOnCreateEmprestimo(EmprestimoDTO emprestimoDTO){
        Long idAluno = emprestimoDTO.codAluno().getCodAluno();
        LocalDate hoje = LocalDate.now();
        List<Emprestimo> emprestimoList = emprestimoRepository.findAll();

        for(Emprestimo emprestimo : emprestimoList){
            if(emprestimo.getCodAluno().getCodAluno().equals(idAluno) && emprestimo.getDataPrevistaDevolucao().isAfter(hoje)){
                throw new IllegalStateException("O aluno de id "+idAluno+" já possui um empréstimo ativo! Data de entrega prevista para "+emprestimo.getDataPrevistaDevolucao()+".");
            }
        }

    }

    /*
    * Método programado para ser executado todo dia à meia noite
    *
    * */
    @Scheduled(cron = "0 0 0 * * *")
    public void validaAtraso(){
        LocalDate hoje = LocalDate.now();
        List<Emprestimo> emprestimoList = emprestimoRepository.findAll();

        for(Emprestimo emprestimo : emprestimoList){
            if (emprestimo.getDataPrevistaDevolucao().isBefore(hoje) && !emprestimo.isFoiDevolvido()){
                emprestimo.setAtrasado(true);
            }
            emprestimoRepository.save(emprestimo);
        }

    }


}
