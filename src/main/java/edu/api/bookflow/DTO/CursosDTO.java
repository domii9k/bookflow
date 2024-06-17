package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursosDTO(@NotBlank String descricao, @NotNull Integer status) {

}
