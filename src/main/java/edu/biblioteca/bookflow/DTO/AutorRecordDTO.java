package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutorRecordDTO(@NotBlank String nome, @NotNull Integer status) {

}
