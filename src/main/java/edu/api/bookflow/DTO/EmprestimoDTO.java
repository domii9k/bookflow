package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EmprestimoDTO(
        @Positive Long codEmprestimo,
        @NotNull Boolean isCancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull Usuario respEmprestimo,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevDevolucao,
        @NotNull Boolean isAtrasado,
        @NotNull Boolean foiDevolvido,
        @NotNull String observacao) {
}
