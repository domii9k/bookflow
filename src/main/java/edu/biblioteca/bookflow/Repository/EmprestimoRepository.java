package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.biblioteca.bookflow.Model.Editora;

@Repository
public interface EmprestimoRepository extends JpaRepository<Editora, Long>{

}
