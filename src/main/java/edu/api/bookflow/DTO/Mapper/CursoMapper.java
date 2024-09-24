package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.CursoDTO;
import edu.api.bookflow.Model.Curso;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO convertToDto(Curso model) {
        if (model == null) {
            return null;
        }
        return new CursoDTO(
                model.getCodCurso(),
                model.getDescricao(),
                model.getStatus()
        );
    }

    public Curso convertToEntity(CursoDTO dto) {
        if (dto == null) {
            return null;
        }
        Curso model = new Curso();
        if (dto.codCurso() != null) {
            model.setCodCurso(dto.codCurso());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
