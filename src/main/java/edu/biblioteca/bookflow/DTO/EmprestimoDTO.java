package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotNull;
import java.sql.Date;

import edu.biblioteca.bookflow.Model.*;
import io.micrometer.common.lang.Nullable;

public record EmprestimoDTO(
        @NotNull Integer cancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull Cursos codCurso,
        @NotNull UsuarioSistema codRespEmprestimo,
        @Nullable UsuarioSistema codRespDevolucao,
        @NotNull Date dataEmprestimo,
        @NotNull Date dataDevolucao,
        @NotNull StatusDevolucaoEnum atrasado) {
}
