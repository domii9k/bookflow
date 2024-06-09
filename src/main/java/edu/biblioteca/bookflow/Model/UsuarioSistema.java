package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="usuario_sistema")
public class UsuarioSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_usuario")
    private Long codUsuario;

    @Column(name = "nome", nullable = false, length = 99)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 99)
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    @Column(name = "is_adm", columnDefinition = "INTEGER DEFAULT 1")
    private Integer isAdm = 1;

    @Column(name = "cpf", nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 0")
    private Integer status = 0;
}
