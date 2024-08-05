package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LivroDTO(
        @Positive Long codLivro,
        @NotBlank String titulo,
        @NotBlank String isbn,
        @NotBlank String patrimonio,
        Long codCurso,
        Long codCategoria,
        BigDecimal edicao,
        @NotNull Integer ano,
        String descricao,
        Long codAutor,
        Long codEditora,
        @NotNull Integer sttsEmprestado,
        @NotNull Integer status) {
}
