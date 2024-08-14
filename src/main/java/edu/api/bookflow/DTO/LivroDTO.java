package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LivroDTO(
        @Positive Long codLivro,
        @NotBlank String titulo,
        String isbn,
        @NotBlank String patrimonio,
        @NotNull Long codCurso,
        Integer edicao,
        @NotNull Integer ano,
        String descricao,
        @NotNull Long codAutor,
        @NotNull Long codEditora,
        @NotNull boolean sttsEmprestado,
        @NotNull boolean statusAtivo) {
}
