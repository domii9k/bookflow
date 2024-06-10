package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.biblioteca.bookflow.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
