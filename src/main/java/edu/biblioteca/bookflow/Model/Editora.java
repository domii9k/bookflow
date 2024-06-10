package edu.biblioteca.bookflow.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="editora")
public class Editora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cod_editora")
    private Long codEditora;

    @Column(name="nome_fantasia", nullable = false)
    private String nomeFantasia;

    @Column(name="endereco")
    private String endereco;

    @Column(name="site")
    private String site;

    @Column(name = "status", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

}
