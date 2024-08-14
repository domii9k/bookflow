package edu.api.bookflow.Model;

import lombok.Getter;

@Getter
public enum PermissoesEnum {

    ADMINISTRADOR("ADMINISTRADOR"),
    BIBLIOTECARIO("BIBLIOTECARIO");

    private final String descricao;

    PermissoesEnum(String descricao) {
        this.descricao = descricao;
    }

}
