package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.CursosDTO;
import edu.api.bookflow.Model.Cursos;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursosDTO convertToDto(Cursos model) {
        if (model == null) {
            return null;
        }
        return new CursosDTO(
                model.getCodCurso(),
                model.getDescricao(),
                model.getStatus()
        );
    }

    public Cursos convertToEntity(CursosDTO dto) {
        if (dto == null) {
            return null;
        }
        Cursos model = new Cursos();
        if (dto.codCurso() != null) {
            model.setCodCurso(dto.codCurso());
        }
        BeanUtils.copyProperties(dto, model);
        return model;
    }
}
