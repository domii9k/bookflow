package edu.api.bookflow.Model;


import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "emprestimo")
public class Emprestimo extends RepresentationModel<Emprestimo> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_emp")
    private Long codEmprestimo;

    @Column(name = "cancelado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer cancelado = 0;

    @ManyToOne
    @JoinColumn(name = "cod_livro", nullable = false, columnDefinition = "INTEGER")
    private Livro codLivro;

    @ManyToOne
    @JoinColumn(name = "cod_aluno", nullable = false, columnDefinition = "INTEGER")
    private Aluno codAluno;

    @ManyToOne
    @JoinColumn(name = "resp_emp", nullable = false, updatable = false, columnDefinition = "INTEGER")
    private UsuarioSistema respEmprestimo;

    @Column(name = "data_emp", nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate dataEmprestimo;

    @Column(name = "data_prev_devolucao", nullable = false, columnDefinition = "DATE")
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "atrasado", columnDefinition = "INTEGER DEFAULT 0")
    private Integer atrasado = 0;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "foi_devolvido", columnDefinition = "INTEGER DEFAULT 0")
    private Integer foiDevolvido = 0;

    // este metodo fará com que, no momento da criação do emprestimo, a data atual será automaticamente preenchida
    @PrePersist
    protected void onCreate() {
        this.dataEmprestimo = LocalDate.now();
    }
}
