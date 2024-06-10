package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "curso")
public class Cursos {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_curso")
    private Long codCurso;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

}
