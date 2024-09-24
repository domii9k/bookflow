package edu.api.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@Entity
@EqualsAndHashCode(callSuper=true)
@Table(name = "livro")
public class Livro extends RepresentationModel<Livro>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_livro", columnDefinition = "SERIAL PRIMARY KEY")
    private Long codLivro;

    @Column(name = "titulo", nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @Column(name = "isbn", unique = true, columnDefinition = "TEXT")
    private String isbn;

    @Column(name = "patrimonio", unique = true, nullable = false, columnDefinition = "TEXT")
    private String patrimonio;

    @ManyToOne
    @JoinColumn(name = "cod_curso", nullable = false, columnDefinition = "INTEGER")
    private Curso curso;

    @Column(name = "edicao", columnDefinition = "INTEGER")
    private Integer edicao;

    @Column(name = "ano", nullable = false, columnDefinition = "INTEGER")
    private Integer ano;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cod_autor", nullable = false, columnDefinition = "INTEGER")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "cod_editora", nullable = false, columnDefinition = "INTEGER")
    private Editora editora;

    @Column(name = "stts_emprestado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean sttsEmprestado;

    @Column(name = "stts_ativo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;

    @PrePersist
    protected void onCreate() {
        this.status = true;
        this.sttsEmprestado = false;
    }

}