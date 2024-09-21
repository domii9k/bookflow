package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Livro;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record EmprestimoDTO(
        @Positive Long codEmprestimo,
        @NotNull Boolean isCancelado,
        @NotNull Livro codLivro,
        @NotNull AlunoFiltradoDTO aluno,
        @NotNull UsuarioFiltradoDTO respEmprestimo,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevDevolucao,
        @NotNull Boolean isAtrasado,
        @NotNull Boolean foiDevolvido,
        @NotNull String observacao) {
}
