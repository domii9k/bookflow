package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Cursos;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.UsuarioSistema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoDTO(
        @NotNull Long codEmprestimo,
        Integer isCancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull Cursos codCurso,
        @NotNull UsuarioSistema codRespEmprestimo,
        UsuarioSistema codRespDevolucao,
        LocalDate dataEmprestimo,
        @NotNull LocalDate dataDevolucao,
        @NotNull Integer isAtrasado,
        String observacao) {
}
