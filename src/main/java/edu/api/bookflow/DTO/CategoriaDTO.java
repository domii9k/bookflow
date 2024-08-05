package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoriaDTO(@Positive Long codCategoria,@NotBlank String descricao, @NotNull Integer status) {

}
