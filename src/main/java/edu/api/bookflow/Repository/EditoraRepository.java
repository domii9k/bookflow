package edu.api.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{

}
