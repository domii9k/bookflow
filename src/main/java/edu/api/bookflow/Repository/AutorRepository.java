package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.api.bookflow.Model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{
    Page<Autor> findByStatus(@Param("status") Boolean status, Pageable pageable);
}
