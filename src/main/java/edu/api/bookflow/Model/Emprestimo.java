package edu.api.bookflow.Model;


import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="emprestimo")
public class Emprestimo extends RepresentationModel<Emprestimo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_emp")
    private Long codEmprestimo;

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
    @JoinColumn(name="resp_dev")
    private UsuarioSistema codRespDevolucao;

    @Column(name="data_emp", nullable = false, updatable = false)
    private LocalDate dataEmprestimo;

    @Column(name="data_dev", nullable = false)
    private LocalDate dataDevolucao;

    @Column(name="atrasado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer atrasado=0;

    @Column(name="observacao", columnDefinition = "TEXT")
    private String observacao;

    // este metodo fará com que, no momento da criação do emprestimo, a data atual será automaticamente preenchida
    @PrePersist
    protected void onCreate() {
        this.dataEmprestimo = LocalDate.now();
    }
}
