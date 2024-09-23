package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.EditoraDTO;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Services.EditoraService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    EditoraService service;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EditoraDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<EditoraDTO> findAll(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                             @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                             @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                             @RequestParam(value = "sortBy", defaultValue = "nomeFantasia") String sortBy,
                                             @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        return service.findAll(pageNumber, pageSize, status, sortBy, sortDir);
    }

    @PostMapping("/novo")
    @ResponseStatus(code = HttpStatus.CREATED)
    public EditoraDTO create(@RequestBody EditoraDTO editoraDTO) {
        return service.create(editoraDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EditoraDTO update(@PathVariable Long id, @RequestBody EditoraDTO editoraDTO) {
        return service.update(id, editoraDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EditoraDTO updatePatch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return service.updatePatch(id, fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long id) {
        return service.ativaEditora(id);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Object> desativar(@PathVariable Long id) {
        return service.desativaEditora(id);
    }
}
