package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.DTO.UsuarioDTO;
import edu.api.bookflow.DTO.UsuarioFiltradoDTO;
import edu.api.bookflow.Exceptions.ApiHttpResponse;
import edu.api.bookflow.Services.UsuarioService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<UsuarioFiltradoDTO> find(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                                  @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                                  @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                                  @RequestParam(value = "sortBy", defaultValue = "codUsuario") String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
        return service.listMapPagination(pageNumber, pageSize, status, sortBy, sortDir);
    }

    @PostMapping("/novo")
    @ResponseStatus(code = HttpStatus.CREATED)
    public UsuarioDTO create(@RequestBody UsuarioDTO dto) {
        return service.create(dto);
    }

    @RequestMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UsuarioDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UsuarioDTO update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return service.update(id, usuarioDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public UsuarioDTO updatePatch(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return service.updatePatch(id, fields);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Object> desativar(@PathVariable Long id){ return service.desativarUsuario(id);}

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long id){ return service.ativarUsuario(id);}
}
