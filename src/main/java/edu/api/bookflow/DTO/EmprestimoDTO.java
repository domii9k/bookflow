package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.Curso;
import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.UsuarioSistema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmprestimoDTO(
        Long codEmprestimo,
        Boolean isCancelado,
        Livro codLivro,
        Aluno codAluno,
        UsuarioSistema respEmprestimo,
        LocalDate dataEmprestimo,
        LocalDate dataPrevDevolucao,
        Boolean isAtrasado,
        Boolean foiDevolvido,
        String observacao) {
}
