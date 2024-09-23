package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
    Page<Curso> findByStatus(@Param("status") Boolean status, Pageable pageable);
}
