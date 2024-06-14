package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditoraDTO(
        @NotBlank String nomeFantasia,
        String endereco,
        String site,
        @NotNull Integer status) {
}
