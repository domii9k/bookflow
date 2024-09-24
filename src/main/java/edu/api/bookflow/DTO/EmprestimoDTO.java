package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
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
        UsuarioFiltradoDTO respDevolucao,
        @NotNull LocalDate dataEmprestimo,
        @NotNull LocalDate dataPrevDevolucao,
        LocalDate dataDevolucao,
        @NotNull Boolean isAtrasado,
        @NotNull Boolean foiDevolvido,
        @NotNull String observacao) {

    public EmprestimoDTO {

        if (dataEmprestimo == null) {
            dataEmprestimo = LocalDate.now();
        }
        if (isCancelado == null) {
            isCancelado = false;
        }
        if (isAtrasado == null) {
            isAtrasado = false;
        }
        if (foiDevolvido == null) {
            foiDevolvido = false;
        }
    }
}
