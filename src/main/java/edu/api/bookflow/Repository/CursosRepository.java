package edu.api.bookflow.Repository;

import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Long>{
    
}
