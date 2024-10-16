package edu.api.bookflow.Repository;

import edu.api.bookflow.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("SELECT us FROM Usuario us WHERE us.status = ?1")
    Page<Usuario> findAllByStatus(@Param("status") Boolean status, Pageable pageable);

    @Query("SELECT us FROM Usuario us WHERE us.email = ?1")
    List<Usuario> findUsuariosByEmail(String email);

    List<Usuario> findByCpf(String cpf);

    //buscar usuario ja existente
    UserDetails findByEmail(String email);

}
