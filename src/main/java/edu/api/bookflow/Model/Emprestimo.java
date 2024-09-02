package edu.api.bookflow.Model;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Boolean cancelado;

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

    @Column(name = "data_prevista_dev", nullable = false, columnDefinition = "DATE")
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "atrasado", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean atrasado;

    @Column(name = "observacao", columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "foi_devolvido", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean foiDevolvido;

    // este metodo fará com que, no momento da criação do emprestimo, a data atual será automaticamente preenchida
    @PrePersist
    protected void onCreate() {
        if(this.dataEmprestimo==null){
            this.dataEmprestimo = LocalDate.now();
        }
        this.atrasado = false;
        this.foiDevolvido = false;
        this.cancelado=false;
    }
}
