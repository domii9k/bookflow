package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CursosDTO(@Positive Long codCurso,@NotBlank String descricao, @NotNull Integer status) {

}
