package edu.api.bookflow.Controller;

import edu.api.bookflow.DTO.AlunoDTO;
import edu.api.bookflow.Model.Aluno;
import edu.api.bookflow.Model.RespostaHttp;
import edu.api.bookflow.Repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping("/{codAluno}")
    public ResponseEntity<Object> getAluno(@PathVariable Long codAluno) {
        Optional<Aluno> aluno = alunoRepository.findById(codAluno);

        try {
            if (aluno.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado!");
            }
            aluno.get()
                    .add(linkTo(methodOn(AlunoController.class).getAllAlunos())
                            .withRel("Lista de Alunos"));
            return ResponseEntity.status(HttpStatus.OK).body(aluno.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro retornar o aluno");
        }
    }

    @GetMapping
    public ResponseEntity<RespostaHttp<Aluno>> getAllAlunos() {
        List<Aluno> listaAluno = alunoRepository.findAll(); // puxamos toda a lista de alunos do
        // repositorio
        try {
            if (!listaAluno.isEmpty()) { // verificando se a lista não está vazia
                for (Aluno aluno : listaAluno) { // para um prestimo dentro da lista...
                    Long codAluno = aluno.getCodAluno(); // pegamos o codigo desse aluno

                    aluno.add(linkTo(methodOn(AlunoController.class).getAluno(codAluno))
                            .withSelfRel()); // e adicionamos um link para a lista de todos os alunos
                }
                return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Lista de alunos encontrada.", listaAluno));

            } else {
                return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Nenhum Aluno Encontrado.", null));
            }
        } catch (Exception e) { // tratamento de captura de erro
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao procurar o aluno", null));
        } // fim try/catch

    }


    @PostMapping
    public ResponseEntity<Object> createAluno(@RequestBody @Valid AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        try {
            BeanUtils.copyProperties(alunoDTO, aluno); // Com este método, podemos atribuir as propriedade da
            // classe DTO para a Model,
            // Sem precisarmos "setá-los" atributo por atributo
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao tentar cadastrar um aluno");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoRepository.save(aluno));
    }


    @DeleteMapping("/{codAluno}")
    public ResponseEntity<RespostaHttp<Aluno>> deleteAluno(@PathVariable Long codAluno) {
        Optional<Aluno> alunoOptional = alunoRepository.findById(codAluno);

        if (alunoOptional.isEmpty()) { //aqui verificamos se o ID solicitado existe
            //retorna um NOT_FOUND pois verifica que é verdadeiro (true) que o objeto nao existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RespostaHttp<>("Aluno não encontrado!", null));
        }

        try { //iniciamos o tratamento de erros na solicitação
            // como criamos um objeto acima (alunoOptional) para procurarmos pelo ID (codAluno),
            Aluno aluno = alunoOptional.get(); //agora precisamos resgatar esse ID
            alunoRepository.delete(aluno); //depois podemos deletá-lo
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RespostaHttp<>("Aluno deletado com sucesso", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao deletar o aluno", null));
        }
    }

    
}
