package edu.api.bookflow.Model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario_sistema")
public class Usuario implements UserDetails {

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
    private Boolean status = true;

    public Usuario(String email, String Senha, PermissoesEnum permissao) {
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
    }

    public Usuario(String nome, String sobrenome, String email, String Senha, PermissoesEnum permissao, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.permissao = permissao;
        this.cpf = cpf;
    }

    //retorna as permissoes que cada usuario possui
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.permissao == PermissoesEnum.ADMINISTRADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"), new SimpleGrantedAuthority("ROLE_BIBLIOTECARIO"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_BIBLIOTECARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        // return UserDetails.super.isEnabled();
        return true;
    }
}
