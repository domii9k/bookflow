package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.CursoDTO;
import edu.api.bookflow.DTO.Mapper.CursoMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Curso;
import edu.api.bookflow.Repository.CursoRepository;
import edu.api.bookflow.Services.patchHttpRequest.GlobalPatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Service
@Valid
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private CursoMapper cursoMapper;
    @Autowired
    private GlobalPatch patcher;

    public PaginationDTO<CursoDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                           @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                           @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                           @RequestParam(value = "sortBy", defaultValue = "descricao") String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<Curso> page = cursoRepository.findByStatus(status, PageRequest.of(pageNumber, pageSize, sort));
        List<CursoDTO> list = page.stream().map(cursoMapper::convertToDto).toList();
        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

    public CursoDTO findById(@Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public CursoDTO create(@Valid CursoDTO cursoDTO) {
        return cursoMapper.convertToDto(cursoRepository.save(cursoMapper.convertToEntity(cursoDTO)));
    }

    public CursoDTO update(@Positive Long id, CursoDTO cursoDTO) {
        return cursoRepository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(cursoDTO, registroEncontrado);
            return cursoMapper.convertToDto(cursoRepository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public CursoDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));
        GlobalPatch.globalPatch(fields, curso);
        return cursoMapper.convertToDto(cursoRepository.save(curso));
    }

    public void delete(@Positive Long id){
        cursoRepository.delete(cursoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    public ResponseEntity<Object> ativarCurso(@Positive Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (!curso.getStatus()) {
            curso.setStatus(true);
            cursoRepository.save(curso);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Curso ativado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Curso já se encontra ativado!");
        }
    }

    public ResponseEntity<Object> desativarCurso(@Positive Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new NotFoundObject(id));

        if (curso.getStatus()) {
            curso.setStatus(false);
            cursoRepository.save(curso);
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Curso desativado com sucesso!");
        } else {
            return ApiHttpResponse.responseStatus(HttpStatus.OK, "Curso já se encontra desativado!");
        }
    }
}
