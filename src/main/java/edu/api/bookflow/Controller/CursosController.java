package edu.api.bookflow.Controller;

import java.util.List;
import java.util.Optional;

import edu.api.bookflow.Repository.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpServerErrorException;

import edu.api.bookflow.Model.Cursos;

@RestController
@RequestMapping("/cursos")
public class CursosController {

    @Autowired
    private CursosRepository cursosRepository;

    // metodo para listar todos os cursos
    @GetMapping
    public ResponseEntity<List<Cursos>> getCursos() {
        return ResponseEntity.ok(cursosRepository.findAll());
    }

    @GetMapping("/{codCurso}")
    public ResponseEntity<Optional<Cursos>> getCurso(@PathVariable Long codCurso) {
        try {
            return ResponseEntity.ok(cursosRepository.findById(codCurso));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @SuppressWarnings("rawtypes")
    @PostMapping
    public ResponseEntity cadastraCurso(@RequestBody Cursos curso) {
        try {
            cursosRepository.save(curso);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return null;
        }

    }

    @SuppressWarnings("rawtypes")
    @PutMapping("/{codCurso}")
    public ResponseEntity atualizaCurso(@PathVariable Long codCurso, @RequestBody Cursos curso) {

        try {
            curso.setCodCurso(codCurso);
            cursosRepository.save(curso);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return null;
        }

    }

    @SuppressWarnings("rawtypes")
    @DeleteMapping("/{codCurso}")
    public ResponseEntity deletaCurso(@PathVariable Long codCurso){

        try {
            Cursos curso = cursosRepository.findById(codCurso).get();
            cursosRepository.delete(curso);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
