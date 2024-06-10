package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotNull;
import java.sql.Date;

public record EmprestimoRecordDTO(
        @NotNull Integer cancelado,
        @NotNull Long codLivro,
        @NotNull Long codAluno,
        @NotNull Long codCurso,
        @NotNull Long codRespEmprestimo,
        @NotNull Long codRespDevolucao,
        @NotNull Date dataEmprestimo,
        @NotNull Date dataDevolucao,
        @NotNull Integer atrasado) {
}
