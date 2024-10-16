package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Services.AlunoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<AlunoDTO> findAllByStatus(@RequestParam(name = "pag", defaultValue = "0") @PositiveOrZero int pageNumber,
                                                   @RequestParam(name = "size", defaultValue = "10") @Positive @Max(50) int pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = "nomeCompleto") String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
                                                   @RequestParam(name = "status", defaultValue = "true") Boolean status) {
        return service.findAllByStatus(pageNumber, pageSize, sortBy, sortDir, status);
    }

    @RequestMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AlunoDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AlunoDTO create(@RequestBody AlunoDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AlunoDTO update(@PathVariable Long id, @RequestBody AlunoDTO dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public AlunoDTO patch(@PathVariable Long id, @RequestBody Map<String,Object> fields){return service.patch(id, fields);}

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){service.delete(id);}

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<Object> desativar(@PathVariable Long id){return service.desativarAluno(id);}

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<Object> ativar(@PathVariable Long id){return service.ativarAluno(id);}
}
