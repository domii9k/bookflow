package edu.biblioteca.bookflow.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.biblioteca.bookflow.Model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
