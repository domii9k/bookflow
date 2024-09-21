package edu.api.bookflow.DTO;

import edu.api.bookflow.Model.PermissoesEnum;

public record UsuarioFiltradoDTO(Long codUsuario,
                                 String nome,
                                 String sobrenome,
                                 PermissoesEnum permissao) {
}
