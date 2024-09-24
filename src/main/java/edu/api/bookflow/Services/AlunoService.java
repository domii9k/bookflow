package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.DTO.Mapper.AlunoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Repository.AlunoRepository;
import edu.api.bookflow.Repository.EmprestimoRepository;
import edu.api.bookflow.Services.patchHttpRequest.GlobalPatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Validated
@Service
public class AlunoService {

    @Autowired
    private final AlunoMapper alunoMapper;
    @Autowired
    private final AlunoRepository alunoRepository;
    @Autowired
    private GlobalPatch patcher;
    @Autowired
    private EmprestimoService emprestimoService;

    public AlunoService(AlunoMapper alunoMapper, AlunoRepository alunoRepository) {
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public PaginationDTO<AlunoDTO> findAllByStatus(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                                   @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = "nomeCompleto") String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
                                                   @RequestParam(name = "status", defaultValue = "true") Boolean status) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Aluno> page = alunoRepository.findAllByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<AlunoDTO> list = page.stream().map(alunoMapper::convertToDto).toList();

        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());

    }

    public AlunoDTO findById(@Positive @NotNull Long id) {
        return alunoRepository.findById(id).map(alunoMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public AlunoDTO create(@Valid AlunoDTO dto) {
        return alunoMapper.convertToDto(alunoRepository.save(alunoMapper.convertToEntity(dto)));
    }

    public AlunoDTO update(@Positive Long id, @Valid AlunoDTO dto) {
        return alunoRepository.findById(id)
                .map(registroEncontrado -> {
                    BeanUtils.copyProperties(dto, registroEncontrado);
                    return alunoMapper.convertToDto(alunoRepository.save(registroEncontrado));
                }).orElseThrow(() -> new NotFoundObject(id));
    }

    public AlunoDTO patch(@Positive Long id, @Valid Map<String,Object> fields) {
        Aluno alunoExistente = alunoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        GlobalPatch.globalPatch(fields, alunoExistente);
        return alunoMapper.convertToDto(alunoRepository.save(alunoExistente));
    }

    public void delete(@Positive Long id) {
        alunoRepository.delete(alunoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> ativarAluno(@Positive Long id){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(()-> new NotFoundObject(id));
        if(!aluno.getStatus()){
            aluno.setStatus(true);
            alunoRepository.save(aluno);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Aluno ativado com sucesso!");
        }else{
            return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "Aluno já se encontra ativo!");
        }
    }

    public ResponseEntity<Object> desativarAluno(@Positive Long id){
      Aluno aluno = alunoRepository.findById(id).orElseThrow(()-> new NotFoundObject(id));
      boolean bool = emprestimoService.findAlunoByAlunoAndData(aluno);

      if (bool){
          throw new IllegalStateException("Desculpe! Parece que o aluno possui um empréstimo em aberto e não pode ser desativado!");
      } else if (!aluno.getStatus()) {
          return ApiHttpResponse.responseStatus(HttpStatus.NOT_MODIFIED, "Aluno já se encontra desativado!");
      } else {
          aluno.setStatus(false);
          alunoRepository.save(aluno);
          return ApiHttpResponse.responseStatus(HttpStatus.OK, "Aluno desativado com sucesso!");

      }
    }

}
