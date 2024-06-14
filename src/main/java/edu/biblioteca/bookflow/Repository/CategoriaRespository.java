package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.biblioteca.bookflow.Model.Categoria;

@Repository
public interface CategoriaRespository  extends JpaRepository<Categoria, Long>{

}