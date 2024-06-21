package edu.api.bookflow.Controller;

import edu.api.bookflow.Model.Livro;
import edu.api.bookflow.Model.RespostaHttp;
import edu.api.bookflow.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @GetMapping("/{codLivro}")
    public ResponseEntity<Object> getLivro(@PathVariable Long codLivro){
        Optional<Livro> livro = livroRepository.findById(codLivro);

        try {
            if (livro.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
            }
            livro.get()
                    .add(linkTo(methodOn(LivroController.class).getAllLivros())
                            .withRel("Lista de Alunos"));
            return ResponseEntity.status(HttpStatus.OK).body(livro.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro retornar o livro");
        }
    }

    @GetMapping
    public ResponseEntity<RespostaHttp<Livro>> getAllLivros(){
        List<Livro> listaLivro = livroRepository.findAll(); // puxamos toda a lista de alunos do
        // repositorio
        try {
            if (!listaLivro.isEmpty()) { // verificando se a lista não está vazia
                for (Livro livro : listaLivro) {
                    Long codLivro = livro.getCodLivro(); // pegamos o codigo desse aluno

                    livro.add(linkTo(methodOn(LivroController.class).getLivro(codLivro))
                            .withSelfRel()); // e adicionamos um link para a lista de todos os alunos
                }
                return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Livros encontrados.", listaLivro));
            } else{
                return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Nenhum livro Encontrado.", null));
            }
        } catch (Exception e) { // tratamento de captura de erro
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao procurar o Livro", null));
        } // fim try/catch

    }
    
}
