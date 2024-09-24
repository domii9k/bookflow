package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EditoraDTO(
        @Positive Long codEditora,
        @NotBlank String nomeFantasia,
        String endereco,
        String site,
        @NotNull Boolean status) {
    public EditoraDTO {
        if (status == null) {
            status = true;
        }
    }
}
