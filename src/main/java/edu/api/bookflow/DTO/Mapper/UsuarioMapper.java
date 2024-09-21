package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.UsuarioDTO;
import edu.api.bookflow.Model.Usuario;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSistemaMapper {

    public UsuarioDTO convertToDto(Usuario model) {
        if (model == null) {
            return null;
        }
        return new UsuarioDTO(
                model.getCodUsuario(),
                model.getNome(),
                model.getSobrenome(),
                model.getEmail(),
                model.getSenha(),
                model.getPermissao(),
                model.getCpf(),
                model.getStatus()
        );
    }

    public Usuario convertToEntity(@NotNull UsuarioDTO dto) {
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
