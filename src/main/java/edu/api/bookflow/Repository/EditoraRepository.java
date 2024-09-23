package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import edu.api.bookflow.Model.Editora;

import java.util.List;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{
    Page<Editora> findByStatus(@Param("status") Boolean status, Pageable pageable);
}

