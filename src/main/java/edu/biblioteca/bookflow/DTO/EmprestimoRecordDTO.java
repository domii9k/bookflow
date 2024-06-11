package edu.biblioteca.bookflow.DTO;

import jakarta.validation.constraints.NotNull;
import java.sql.Date;

import edu.biblioteca.bookflow.Model.*;

public record EmprestimoRecordDTO(
        @NotNull Integer cancelado,
        @NotNull Livro codLivro,
        @NotNull Aluno codAluno,
        @NotNull Cursos codCurso,
        @NotNull UsuarioSistema codRespEmprestimo,
        UsuarioSistema codRespDevolucao,
        @NotNull Date dataEmprestimo,
        @NotNull Date dataDevolucao,
        @NotNull Integer atrasado) {
}
