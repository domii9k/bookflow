package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.PermissoesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UsuarioDTO(
        @Positive Long codUsuario,
        @NotBlank String nome,
        @NotBlank String sobrenome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotNull PermissoesEnum permissao,
        @NotBlank String cpf,
        @NotNull Boolean statusAtivo) {

    public UsuarioDTO {
        if (statusAtivo == null) {
            statusAtivo = true;
        }
    }
}
