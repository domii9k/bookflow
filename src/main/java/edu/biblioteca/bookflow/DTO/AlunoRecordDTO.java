package edu.biblioteca.bookflow.DTO;

import edu.biblioteca.bookflow.Model.Cursos;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRecordDTO(@NotBlank String nomeCompleto, @NotBlank String ra, @NotBlank String cpf,
        @NotNull Cursos codCurso, @NotBlank @Email String email, @NotBlank String tel, @Nullable String tel2,
        @NotNull Integer status, @NotBlank String cep, @NotBlank String endereco) {

}
