package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_livro")
    private Long codLivro;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "patrimonio", unique = true, nullable = false)
    private String patrimonio;

    @ManyToOne
    @JoinColumn(name = "cod_curso", nullable = false)
    private Cursos curso;

    @ManyToOne
    @JoinColumn(name = "cod_categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "edicao", precision = 2, scale = 0)
    private BigDecimal edicao;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cod_autor", nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "cod_editora", nullable = false)
    private Editora editora;

    @Column(name = "stts_emprestado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer sttsEmprestado = 0;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

}