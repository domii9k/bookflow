package edu.api.bookflow.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.api.bookflow.Model.Autor;
import edu.api.bookflow.Model.Curso;
import edu.api.bookflow.Model.Editora;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LivroDTO(
        @Positive Long codLivro,
        @NotBlank String titulo,
        String isbn,
        @NotBlank String patrimonio,
        @NotNull CursoDTO codCurso,
        Integer edicao,
        @NotNull Integer ano,
        String descricao,
        @NotNull AutorDTO codAutor,
        @NotNull EditoraDTO codEditora,
        @NotNull Boolean sttsEmprestado,
        @NotNull Boolean statusAtivo) {

    public LivroDTO {
        if (sttsEmprestado == null) {
            sttsEmprestado = false;
        }
        if (statusAtivo == null) {
            statusAtivo = true;
        }
    }
}
