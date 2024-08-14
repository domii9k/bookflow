package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Curso;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.UsuarioSistema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoDTO(
        @NotNull Long codEmprestimo,
        Integer isCancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull UsuarioSistema codRespEmprestimo,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevDevolucao,
        @NotNull Integer isAtrasado,
        Integer foiDevolvido,
        String observacao) {
}
