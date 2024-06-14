package edu.biblioteca.bookflow.Model;

public enum PermissoesEnum {

    ADMINISTRADOR("administrador"),
    GERENTE("gerente");

    private String descricao;

    PermissoesEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
