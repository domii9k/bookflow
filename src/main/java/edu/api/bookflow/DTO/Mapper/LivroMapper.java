package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.*;
import edu.api.bookflow.Model.*;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    // Converte Livro -> LivroDTO
    public LivroDTO convertToDto(Livro model) {
        if (model == null) {
            return null;
        }

        // Convertendo entidades aninhadas para DTOs
        CursoDTO cursoDTO = new CursoDTO(
                model.getCurso().getCodCurso(),
                model.getCurso().getDescricao(),
                model.getCurso().getStatus()
        );

        AutorDTO autorDTO = new AutorDTO(
                model.getAutor().getCodAutor(),
                model.getAutor().getNome(),
                model.getAutor().getStatus()
        );

        EditoraDTO editoraDTO = new EditoraDTO(
                model.getEditora().getCodEditora(),
                model.getEditora().getNomeFantasia(),
                model.getEditora().getEndereco(),
                model.getEditora().getSite(),
                model.getEditora().getStatus()
        );

        return new LivroDTO(
                model.getCodLivro(),
                model.getTitulo(),
                model.getIsbn(),
                model.getPatrimonio(),
                cursoDTO,  // Usando DTO ao invés da entidade
                model.getEdicao(),
                model.getAno(),
                model.getDescricao(),
                autorDTO,  // Usando DTO ao invés da entidade
                editoraDTO,  // Usando DTO ao invés da entidade
                model.getSttsEmprestado(),
                model.getStatus());
    }

    // Converte LivroDTO -> Livro
    public Livro convertToEntity(LivroDTO dto) {
        if (dto == null) {
            return null;
        }

        Livro model = new Livro();

        if (dto.codLivro() != null) {
            model.setCodLivro(dto.codLivro());
        }

        model.setTitulo(dto.titulo());
        model.setIsbn(dto.isbn());
        model.setPatrimonio(dto.patrimonio());

        // Convertendo DTOs aninhados de volta para entidades
        Curso curso = new Curso();
        curso.setCodCurso(dto.codCurso().codCurso());
        model.setCurso(curso);

        Autor autor = new Autor();
        autor.setCodAutor(dto.codAutor().codAutor());
        model.setAutor(autor);

        Editora editora = new Editora();
        editora.setCodEditora(dto.codEditora().codEditora());
        model.setEditora(editora);

        model.setEdicao(dto.edicao());
        model.setAno(dto.ano());
        model.setDescricao(dto.descricao());
        model.setSttsEmprestado(dto.sttsEmprestado());
        model.setStatus(dto.statusAtivo());

        return model;
    }
}
