package edu.biblioteca.bookflow.Model;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="emprestimo")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_emp")
    private Integer codEmprestimo;

    @Column(name = "cancelado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer cancelado=0;

    @ManyToOne
    @JoinColumn(name="cod_livro", nullable = false)
    private Livro codLivro;

    @ManyToOne
    @JoinColumn(name="cod_aluno", nullable = false)
    private Aluno codAluno;

    @ManyToOne
    @JoinColumn(name="cod_curso", nullable = false)
    private Cursos codCurso;
    
    @ManyToOne
    @JoinColumn(name="resp_emp", nullable = false)
    private UsuarioSistema codRespEmprestimo;

    @ManyToOne
    @JoinColumn(name="resp_dev", nullable = false)
    private UsuarioSistema codRespDevolucao;

    @Column(name="data_emp", nullable = false)
    private Date dataEmprestimo;

    @Column(name="data_dev", nullable = false)
    private Date dataDevolucao;

    @Column(name="atrasado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer atrasado=0;
   
}
