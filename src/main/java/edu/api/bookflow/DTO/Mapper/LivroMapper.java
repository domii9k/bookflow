package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.LivroDTO;
import edu.api.bookflow.Model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public LivroDTO convertToDto(Livro model) {
        if (model == null) {
            return null;
        }
        return new LivroDTO(
                model.getCodLivro(),
                model.getTitulo(),
                model.getIsbn(),
                model.getPatrimonio(),
                model.getCurso().getCodCurso(),
                model.getEdicao(),
                model.getAno(),
                model.getDescricao(),
                model.getAutor().getCodAutor(),
                model.getEditora().getCodEditora(),
                model.isSttsEmprestado(),
                model.isStatus()
        );
    }

    public Livro convertToEntity(LivroDTO dto) {
        if (dto == null) {
            return null;
        }
        Livro model = new Livro();
        BeanUtils.copyProperties(dto, model);

        return model;
    }
}
