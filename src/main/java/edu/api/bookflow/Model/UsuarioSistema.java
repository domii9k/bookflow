package edu.api.bookflow.Model;

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

    @Column(name = "nome", nullable = false, columnDefinition = "TEXT")
    private String nome;

    @Column(name = "sobrenome", nullable = false, columnDefinition = "TEXT")
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "senha", nullable = false, columnDefinition = "TEXT")
    private String senha;

    @Column(name = "tipo_permissao", columnDefinition = "permissao DEFAULT 'BIBLIOTECARIO'")
    private PermissoesEnum tipoPermissao = PermissoesEnum.BIBLIOTECARIO;

    @Column(name = "cpf", nullable = false, unique = true, columnDefinition = "TEXT")
    private String cpf;

    @Column(name = "stts_ativo", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;
}
