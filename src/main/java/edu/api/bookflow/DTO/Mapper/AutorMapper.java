package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.AutorDTO;
import edu.api.bookflow.Model.Autor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AutorMapper {

    public AutorDTO convertToDto(Autor model) {
        if (model == null) {
            return null;
        }
        return new AutorDTO(
                model.getCodAutor(),
                model.getNome(),
                model.isStatus()
        );
    }

    public Autor convertToEntity(AutorDTO dto) {
        if (dto == null) {
            return null;
        }
        Autor model = new Autor();
        if (dto.codAutor() != null) {
            model.setCodAutor(dto.codAutor());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
