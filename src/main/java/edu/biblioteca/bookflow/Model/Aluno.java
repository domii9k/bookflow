package edu.biblioteca.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "aluno")
public class Aluno extends RepresentationModel<Aluno> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_aluno")
    private Long codAluno;

    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "ra", nullable = false)
    private String ra;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "cod_curso", nullable = false)
    private Cursos codCurso;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "tel_2")
    private String tel2;

    @Column(name = "stts_ativo", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

    @Column(name = "cep", nullable = false)
    private String cep;

    @Column(name = "endereco", nullable = false)
    private String endereco;
}
