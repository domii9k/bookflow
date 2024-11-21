package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.LivroDTO;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Services.LivroService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroService service;

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public LivroDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<LivroDTO> findAll(@RequestParam(name = "pag",defaultValue = "0") @PositiveOrZero int pageNumber,
                                           @RequestParam(name = "size",defaultValue = "50") @Positive @Max(50) int pageSize,
                                           @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                           @RequestParam(value = "sortBy", defaultValue = "titulo") String sortBy,
                                           @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        return service.findAll(pageNumber, pageSize, status, sortBy, sortDir);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LivroDTO create(@RequestBody LivroDTO livroDTO) {
        return service.create(livroDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public LivroDTO update(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        return service.update(id, livroDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public LivroDTO updatePatch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return service.updatePatch(id, fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long id){return service.ativarLivro(id);}

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Object> desativar(@PathVariable Long id){return service.desativarLivro(id);}
}
