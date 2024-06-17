package edu.api.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.api.bookflow.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
