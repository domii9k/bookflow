package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.biblioteca.bookflow.Model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
