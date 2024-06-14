package edu.biblioteca.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name = "curso")
public class Cursos extends RepresentationModel<Cursos>{

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_curso")
    private Long codCurso;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "stts_ativo", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

}
