package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Curso;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AlunoDTO(@Positive Long codAluno,
                       String nomeCompleto,
                       String ra,
                       String cpf,
                       Curso codCurso,
                       @Email String email,
                       String tel,
                       String tel2,
                       Boolean status,
                       String cep,
                       String endereco) {

}
