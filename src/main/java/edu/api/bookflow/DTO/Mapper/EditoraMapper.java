package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.EditoraDTO;
import edu.api.bookflow.Model.Editora;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EditoraMapper {

    public EditoraDTO convertToDto(Editora model) {
        if (model == null) {
            return null;
        }
        return new EditoraDTO(
                model.getCodEditora(),
                model.getNomeFantasia(),
                model.getEndereco(),
                model.getSite(),
                model.getStatus()
        );
    }

    public Editora convertToEntity(EditoraDTO dto) {
        if (dto == null) {
            return null;
        }
        Editora model = new Editora();
        if (dto.codEditora() != null) {
            model.setCodEditora(dto.codEditora());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
