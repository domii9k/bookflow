package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Curso;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AlunoDTO(@Positive Long codAluno, @NotBlank String nomeCompleto, @NotBlank String ra, @NotBlank String cpf,
                       @NotNull Curso codCurso, @NotBlank @Email String email, @NotBlank String tel, String tel2,
                       @NotNull Integer status, @NotBlank String cep, @NotBlank String endereco) {

}
