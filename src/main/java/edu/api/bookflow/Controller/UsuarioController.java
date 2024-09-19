package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Services.UsuarioService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioSistemaController {

    @Autowired
    private UsuarioService service;

    /*@GetMapping
    public List<Usuario> list(@RequestParam(defaultValue = "true") PermissoesEnum permissao){
        return service.teste(permissao);
    }*/

    @GetMapping
    public PaginationDTO<Map<String, Object>> find(@RequestParam(name = "pag",defaultValue = "0") @PositiveOrZero int pageNumber,
                                                   @RequestParam(name = "size",defaultValue = "5") @Positive @Max(50) int pageSize,
                                                   @RequestParam(name = "status", defaultValue = "true") Boolean status,
                                                   @RequestParam(value = "sortBy", defaultValue = "codUsuario") String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir){
        return service.listMapPagination(pageNumber,pageSize,status,sortBy,sortDir);
    }
}
