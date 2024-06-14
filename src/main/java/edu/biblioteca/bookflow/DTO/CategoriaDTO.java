package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoriaDTO(@NotBlank String descricao, @NotNull Integer status) {

}
