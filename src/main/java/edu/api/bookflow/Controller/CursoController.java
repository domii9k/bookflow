package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.CursoDTO;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Services.CursoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    CursoService service;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CursoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<CursoDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                           @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                           @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                           @RequestParam(value = "sortBy", defaultValue = "descricao") String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        return service.findAll(pageNumber, pageSize, status, sortBy, sortDir);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CursoDTO create(@RequestBody CursoDTO cursoDTO) {
        return service.create(cursoDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CursoDTO update(@PathVariable Long id, @RequestBody CursoDTO cursoDTO) {
        return service.update(id, cursoDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public CursoDTO updatePatch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return service.updatePatch(id, fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long id) {
        return service.ativarCurso(id);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Object> desativar(@PathVariable Long id) {
        return service.desativarCurso(id);
    }
}
