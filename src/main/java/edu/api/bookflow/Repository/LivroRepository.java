package edu.api.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
