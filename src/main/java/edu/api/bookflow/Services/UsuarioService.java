package edu.api.bookflow.Services;

import edu.api.bookflow.DTO.Mapper.UsuarioSistemaMapper;
import edu.api.bookflow.DTO.Pagination.PaginationDTO;
import edu.api.bookflow.DTO.UsuarioDTO;
import edu.api.bookflow.Exceptions.NotFoundObject;
import edu.api.bookflow.Model.Usuario;
import edu.api.bookflow.Repository.UsusarioSistemaRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@Service
public class UsuarioService {

    @Autowired
    private static UsusarioSistemaRepository repository;
    @Autowired
    private static UsuarioSistemaMapper mapper;

    @Autowired
    private GlobalPatch patch;

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
        Page<Usuario> page = repository.findAllByStatus(status, PageRequest.of(pageNumber, pageSize, sort));

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

    public UsuarioDTO findById(@Positive Long id) {
        return repository.findById(id).map(mapper::convertToDto).orElseThrow(() -> new NotFoundObject(id));
    }

    public UsuarioDTO create(@Valid UsuarioDTO usuarioDTO) {
        validaCPFeEmail(mapper.convertToEntity(usuarioDTO));
        return mapper.convertToDto(repository.save(mapper.convertToEntity(usuarioDTO)));
    }

    public UsuarioDTO update(@Positive Long id, @Valid UsuarioDTO usuarioDTO) {
        return repository.findById(id).map(registroEncontrado -> {
            BeanUtils.copyProperties(usuarioDTO, registroEncontrado);
            return mapper.convertToDto(repository.save(registroEncontrado));
        }).orElseThrow(() -> new NotFoundObject(id));
    }

    public UsuarioDTO updatePatch(@Positive Long id, Map<String, Object> fields) {
        Usuario usuario = repository.findById(id).orElseThrow(()-> new NotFoundObject(id));
        validaCPFeEmail(usuario);
        patch.globalPatch(fields, usuario);

        return mapper.convertToDto(repository.save(usuario));
    }

    public void delete(@Positive Long id) {
        repository.delete(repository.findById(id).orElseThrow(() -> new NotFoundObject(id)));
    }

    private static void validaCPFeEmail(Usuario usuario) {
        String email = usuario.getEmail();
        String cpf = usuario.getCpf();
        // Verifica se existe algum usuário com o mesmo CPF ou e-mail
        List<Usuario> usuarioListByEmail = repository.findByEmail(email);
        List<Usuario> usuarioListByCpf = repository.findByCpf(cpf);

        if (!usuarioListByEmail.isEmpty()) {
            throw new IllegalStateException("Já existe um usuário com o e-mail: " + email);
        }
        if (!usuarioListByCpf.isEmpty()) {
            throw new IllegalStateException("Já existe um usuário com o CPF: " + cpf);
        }
    }
}
