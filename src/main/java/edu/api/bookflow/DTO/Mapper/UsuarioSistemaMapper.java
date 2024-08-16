package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.UsuarioSistemaDTO;
import edu.api.bookflow.Model.UsuarioSistema;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UsuarioSistemaMapper {

    public UsuarioSistemaDTO convertToDto(UsuarioSistema model) {
        if (model == null) {
            return null;
        }
        return new UsuarioSistemaDTO(
                model.getCodUsuario(),
                model.getNome(),
                model.getSobrenome(),
                model.getEmail(),
                model.getSenha(),
                model.getTipoPermissao(),
                model.getCpf(),
                model.isStatus()
        );
    }

    public UsuarioSistema convertToEntity(UsuarioSistemaDTO dto) {
        if (dto == null) {
            return null;
        }
        UsuarioSistema model = new UsuarioSistema();
        if (dto.codUsuario() != null) {
            model.setCodUsuario(dto.codUsuario());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
