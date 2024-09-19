package edu.api.bookflow.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_sistema")
public class UsuarioSistema extends RepresentationModel<UsuarioSistema> {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "permissao", columnDefinition = "TEXT DEFAULT 'BIBLIOTECARIO'")
    private PermissoesEnum permissao = PermissoesEnum.BIBLIOTECARIO;

    @Column(name = "cpf", nullable = false, unique = true, columnDefinition = "TEXT")
    private String cpf;

    @Column(name = "stts_ativo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean status = true;

    public UsuarioSistema(Long codUsuario, String nome, String sobrenome, String cpf, PermissoesEnum permissao) {
        this.codUsuario = codUsuario;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.permissao = permissao;
    }
}
