package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.Mapper.UsuarioSistemaMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.Model.PermissoesEnum;
import edu.api.bookflow.Model.UsuarioSistema;
import edu.api.bookflow.Repository.UsusarioSistemaRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@Service
public class UsuarioService {

    private final UsusarioSistemaRepository repository;
    private final UsuarioSistemaMapper mapper;

    public UsuarioService(UsusarioSistemaRepository repository, UsuarioSistemaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public PaginationDTO<Map<String, Object>> listMapPagination(
            @RequestParam(name = "pag") @PositiveOrZero int pageNumber,
            @RequestParam(name = "size") @Positive @Max(50) int pageSize,
            @RequestParam(name = "status", defaultValue = "true") Boolean status,
            @RequestParam(value = "sortBy", defaultValue = "codUsuario") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<UsuarioSistema> page = repository.findAllByStatus(status, PageRequest.of(pageNumber, pageSize, sort));

        List<Map<String, Object>> list = page.stream().map(us -> {
            Map<String, Object> result = new HashMap<>();
            result.put("codUsuario", us.getCodUsuario());
            result.put("nome", us.getNome());
            result.put("sobrenome", us.getSobrenome());
            result.put("cpf", us.getCpf());
            result.put("permissao", us.getPermissao());
            return result;
        }).toList();

        return new PaginationDTO<>(list, page.getTotalElements(), page.getTotalPages());
    }

}
