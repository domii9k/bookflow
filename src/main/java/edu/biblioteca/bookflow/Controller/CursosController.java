package edu.biblioteca.bookflow.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpServerErrorException;

import edu.biblioteca.bookflow.Model.Cursos;
import edu.biblioteca.bookflow.Repository.CursosRepository;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;

    @GetMapping
    public ResponseEntity<List<Cursos>> getCursos() {
        return ResponseEntity.ok(cursosRepository.findAll());
    }

    @GetMapping("/{codCurso}")
    public ResponseEntity<Optional<Cursos>> getCurso(@PathVariable Integer codCurso){
        try {
            return ResponseEntity.ok(cursosRepository.findById(codCurso));
        } catch (Exception e) {
            return null;
        }
       
    }

}
