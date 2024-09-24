package edu.api.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "aluno")
public class Aluno extends RepresentationModel<Aluno> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aluno")
    private Long codAluno;

    @Column(name = "nome_completo", nullable = false, columnDefinition = "TEXT")
    private String nomeCompleto;

    @Column(name = "ra", nullable = false, columnDefinition = "TEXT")
    private String ra;

    @Column(name = "cpf", unique = true, nullable = false, columnDefinition = "TEXT")
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "cod_curso", nullable = false, columnDefinition = "INTEGER")
    private Curso codCurso;

    @Column(name = "email", unique = true, nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "tel", nullable = false, columnDefinition = "TEXT")
    private String tel;

    @Column(name = "tel_2", columnDefinition = "TEXT")
    private String tel2;

    @Column(name = "stts_ativo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @Column(name = "cep", nullable = false, columnDefinition = "TEXT")
    private String cep;

    @Column(name = "endereco", nullable = false, columnDefinition = "TEXT")
    private String endereco;

    @PrePersist
    protected void onCreate() {
        this.status = true;
    }
}
