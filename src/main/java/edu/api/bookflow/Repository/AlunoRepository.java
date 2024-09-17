package edu.api.bookflow.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.api.bookflow.Model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

    Page<Aluno> findAllByStatus(@Param("Status") Boolean status, Pageable pageable);
}
