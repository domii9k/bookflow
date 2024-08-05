package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.PermissoesEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsuarioSistemaDTO(
        @Positive Long codUsuario,
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank @Email String email,
        @NotBlank String senha,
        @NotNull Integer isAdm,
        @NotBlank String cpf,
        @NotNull Integer status) {
}
