package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Cursos;
import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoDTO(@NotBlank String nomeCompleto, @NotBlank String ra, @NotBlank String cpf,
                       @NotNull Cursos codCurso, @NotBlank @Email String email, @NotBlank String tel, String tel2,
                       @NotNull Integer status, @NotBlank String cep, @NotBlank String endereco) {

}
