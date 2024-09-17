package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.DTO.Mapper.AlunoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Repository.AlunoRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Validated
@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;
    @Autowired
    private GlobalPatch patcher;

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

    public AlunoDTO patch(@Positive Long id, @Valid AlunoDTO alunoIncompleto) {
        Aluno alunoExistente = alunoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        try {
            patcher.patch(alunoExistente, alunoMapper.convertToEntity(alunoIncompleto));
            alunoRepository.save(alunoExistente);
        } catch (Exception e) {
            throw new RuntimeException("OPS! Parece que houve um erro ao tentar atualizar o Aluno.\nPor favor, contate o administrador do sistema.");
        }
        return alunoMapper.convertToDto(alunoExistente);
    }

    public void delete(@Positive Long id) {
        alunoRepository.delete(alunoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

}
