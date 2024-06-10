package edu.biblioteca.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="autor")
public class Autor extends RepresentationModel<Autor> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_autor")
    private Long codAutor;
    
    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;
}
