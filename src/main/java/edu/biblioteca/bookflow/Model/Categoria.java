package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="categoria")
public class Categoria {
    
    @Id
    @Column(name="cod_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCategoria;

    @Column(name="descricao", nullable = false)
    private String descricao;
   
    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;
}
