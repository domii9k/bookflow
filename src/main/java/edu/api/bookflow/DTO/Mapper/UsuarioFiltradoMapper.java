package edu.api.bookflow.DTO.Mapper;
import edu.api.bookflow.DTO.UsuarioFiltradoDTO;
import edu.api.bookflow.Model.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFiltradoMapper {
    public UsuarioFiltradoDTO convertToDto(Usuario model) {
        if (model == null) {
            return null;
        }
        return new UsuarioFiltradoDTO(
                model.getCodUsuario(),
                model.getNome(),
                model.getSobrenome(),
                model.getPermissao()
        );
    }

    public Usuario convertToEntity(@NotNull UsuarioFiltradoDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario model = new Usuario();
        if (dto.codUsuario() != null) {
            model.setCodUsuario(dto.codUsuario());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
