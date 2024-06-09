package edu.biblioteca.bookflow.Repository;

import org.springframework.stereotype.Repository;

import edu.biblioteca.bookflow.Model.Cursos;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, Integer>{

    
}
