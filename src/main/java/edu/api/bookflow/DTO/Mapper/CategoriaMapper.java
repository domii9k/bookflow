package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.CategoriaDTO;
import edu.api.bookflow.Model.Categoria;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO convertToDto(Categoria model) {
        if (model == null) {
            return null;
        }
        return new CategoriaDTO(
                model.getCodCategoria(),
                model.getDescricao(),
                model.getStatus()
        );
    }

    public Categoria convertToEntity(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }
        Categoria model = new Categoria();
        if (dto.codCategoria() != null) {
            model.setCodCategoria(dto.codCategoria());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
