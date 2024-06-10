package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.biblioteca.bookflow.Model.UsuarioSistema;

@Repository
public interface UsusarioSistemaRepository extends JpaRepository<UsuarioSistema, Long>{

}
