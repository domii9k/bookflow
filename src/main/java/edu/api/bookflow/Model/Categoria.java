package edu.api.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="categoria")
public class Categoria extends RepresentationModel<Categoria>{
    
    @Id
    @Column(name="cod_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codCategoria;

    @Column(name="descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;
   
    @Column(name = "stts_ativo", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;
}
