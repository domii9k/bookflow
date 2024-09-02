package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Repository.EmprestimoRepository;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Model.RespostaHttp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import edu.api.bookflow.Services.EmprestimoService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService service;

    /*@GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<EmprestimoDTO> listAll(@RequestParam(name = "pag",defaultValue = "0") @PositiveOrZero int pageNumber,
                                                @RequestParam(name = "size",defaultValue = "20") @Positive @Max(50) int pageSize,
                                                @RequestParam(value = "sortBy", defaultValue = "dataEmprestimo") String sortBy,
                                                @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir) {
        return service.listAll(pageNumber, pageSize, sortBy, sortDir);
    }*/

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public PaginationDTO<EmprestimoDTO> listAll(@RequestParam(name = "pag",defaultValue = "0") @PositiveOrZero int pageNumber,
                                             @RequestParam(name = "size",defaultValue = "10") @Positive @Max(50) int pageSize,
                                             @RequestParam(value = "sortBy", defaultValue = "dataEmprestimo") String sortBy,
                                             @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
                                             @RequestParam(name = "cancelado", defaultValue = "false") Boolean cancelado,
                                             @RequestParam(name = "foiDevolvido", defaultValue = "false") Boolean foiDevolvido,
                                             @RequestParam(name = "atrasado", defaultValue = "false") Boolean atrasado){
        return service.listAll(pageNumber, pageSize, sortBy, sortDir, cancelado, foiDevolvido, atrasado);
    }
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EmprestimoDTO findById(@PathVariable @NotNull @Positive Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmprestimoDTO create(@RequestBody @Valid EmprestimoDTO dto){
        return service.create(dto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EmprestimoDTO patch(@PathVariable @Positive Long id,@RequestBody EmprestimoDTO emprestimoDTO){return service.patch(id, emprestimoDTO);}

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public EmprestimoDTO update(@PathVariable Long id, @RequestBody EmprestimoDTO dto){return service.update(id, dto);}

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){service.delete(id);}
}
