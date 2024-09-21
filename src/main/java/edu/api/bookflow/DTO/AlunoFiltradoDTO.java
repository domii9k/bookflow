package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Curso;

public record AlunoFiltradoDTO(Long codAluno,
                               String nomeCompleto,
                               String ra,
                               Curso codCurso,
                               String email,
                               String tel
) {
}
