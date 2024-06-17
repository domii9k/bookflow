package edu.api.bookflow.Model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=true)
@Table(name="editora")
public class Editora extends RepresentationModel<Editora> {
    
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

    @Column(name = "stts_ativo", columnDefinition = "INTEGER DEFAULT 1")
    private Integer status = 1;

}
