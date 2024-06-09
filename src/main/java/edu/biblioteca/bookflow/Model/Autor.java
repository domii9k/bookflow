package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_autor")
    private Integer codAutor;
    
    @Column(name="nome", nullable = false)
    private String nome;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;
}
