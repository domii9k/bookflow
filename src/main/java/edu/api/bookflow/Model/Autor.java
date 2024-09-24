package edu.api.bookflow.Model;

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
    
    @Column(name="nome", nullable = false, columnDefinition = "TEXT")
    private String nome;

    @Column(name = "stts_ativo", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean status = true;
}
