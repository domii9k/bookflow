package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursosRecordDTO(@NotBlank String descricao, @NotNull Integer status) {

}
