package edu.biblioteca.bookflow.DTO;

import edu.biblioteca.bookflow.Model.PermissoesEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioSistemaDTO(
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull PermissoesEnum isAdm,
        @NotBlank String cpf,
        @NotNull Integer status) {
}
