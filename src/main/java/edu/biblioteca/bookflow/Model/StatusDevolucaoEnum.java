package edu.biblioteca.bookflow.Model;

public enum StatusDevolucaoEnum {

    ATIVO("ativo"),
    ATRASADO("atrasado");

    private String descricao;

    StatusDevolucaoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
