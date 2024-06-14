package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AutorDTO(@NotBlank String nome, @NotNull Integer status) {

}
