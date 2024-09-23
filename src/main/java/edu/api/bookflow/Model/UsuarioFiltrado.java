package edu.api.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

public class UsuarioFiltrado extends RepresentationModel<UsuarioFiltrado> {
    private Long codUsuario;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String permissao;

    // Getters e Setters
}