package edu.api.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.api.bookflow.Model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
