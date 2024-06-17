package edu.api.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.UsuarioSistema;

@Repository
public interface UsusarioSistemaRepository extends JpaRepository<UsuarioSistema, Long>{

}
