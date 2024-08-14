package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Curso;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.UsuarioSistema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoDTO(
        @NotNull Long codEmprestimo,
        boolean isCancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull UsuarioSistema codRespEmprestimo,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevDevolucao,
        @NotNull boolean isAtrasado,
        boolean foiDevolvido,
        String observacao) {
}
