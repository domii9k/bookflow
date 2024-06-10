package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record LivroRecordDTO(
        @NotBlank String titulo,
        @NotBlank String isbn,
        @NotBlank String patrimonio,
        @NotNull Long codCurso,
        @NotNull Long codCategoria,
        BigDecimal edicao,
        @NotNull Integer ano,
        String descricao,
        @NotNull Long codAutor,
        @NotNull Long codEditora,
        @NotNull Integer sttsEmprestado,
        @NotNull Integer status) {
}
