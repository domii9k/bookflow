package edu.api.bookflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AutorDTO(@Positive Long codAutor, @NotBlank String nome, @NotNull boolean status) {

}
