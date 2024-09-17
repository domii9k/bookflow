package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.UsuarioSistema;

import java.util.List;

@Repository
public interface UsusarioSistemaRepository extends JpaRepository<UsuarioSistema, Long>{

    @Query("SELECT us FROM UsuarioSistema us WHERE us.status = ?1")
    Page<UsuarioSistema> findAllByStatus(@Param("status") Boolean status, Pageable pageable);

}
