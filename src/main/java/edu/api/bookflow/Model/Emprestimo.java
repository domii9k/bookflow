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

    @Column(name = "cancelado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean cancelado = false;

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

    @Column(name = "atrasado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean atrasado = false;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "foi_devolvido", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean foiDevolvido = false;

    // este metodo fará com que, no momento da criação do emprestimo, a data atual será automaticamente preenchida
    @PrePersist
    protected void onCreate() {
        this.dataEmprestimo = LocalDate.now();
    }
}
