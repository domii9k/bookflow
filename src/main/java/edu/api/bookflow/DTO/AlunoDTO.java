package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Curso;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AlunoDTO(@Positive Long codAluno,
                       @NotBlank  String nomeCompleto,
                       @NotNull String ra,
                       @NotNull String cpf,
                       @NotNull Curso codCurso,
                       @Email String email,
                       @NotNull String tel,
                       String tel2,
                       Boolean status,
                       @NotNull String cep,
                       @NotNull String endereco
) {

}
