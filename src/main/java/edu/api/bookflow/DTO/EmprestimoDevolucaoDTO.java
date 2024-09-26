package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.Usuario;
import jakarta.validation.constraints.NotNull;

public record EmprestimoDevolucaoDTO(
        @NotNull UsuarioFiltradoDTO respDevolucao,
        String observacao
) {
}
