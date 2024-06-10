package edu.biblioteca.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="usuario_sistema")
public class UsuarioSistema extends RepresentationModel<UsuarioSistema>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
    private Long codUsuario;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "is_adm", columnDefinition = "INTEGER DEFAULT 1")
    private Integer isAdm = 1;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 0")
    private Integer status = 0;
}
