package edu.biblioteca.bookflow.Controller;

import edu.biblioteca.bookflow.DTO.EmprestimoRecordDTO;
import edu.biblioteca.bookflow.Model.Emprestimo;
import edu.biblioteca.bookflow.Model.RespostaHttp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import edu.biblioteca.bookflow.Repository.EmprestimoRepository;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    EmprestimoRepository emprestimoRepository;

    /**
     * Método para retornar apenas um emprestimo, seja ele cancelado ou nao
     * 
     * @return Long codEmprestimo - como parametro para retornar informações do
     *         codigo em especifico
     **/
    @GetMapping("/{codEmprestimo}")
    public ResponseEntity<Object> getEmprestimo(@PathVariable Long codEmprestimo) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(codEmprestimo);

        try {
            if (emprestimo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empréstimo não encontrado!");
            }
            emprestimo.get()
                    .add(linkTo(methodOn(EmprestimoController.class).getAllEmprestimos())
                            .withRel("Lista de Empréstimos"));
            return ResponseEntity.status(HttpStatus.OK).body(emprestimo.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro retornar o empréstimo");
        }

    }

    /**
     * Metodo para listar todos os empréstimos ativos do sistema
     * voltado para listar apenas os ativos na pagina de emprestimos
     **/
    @GetMapping
    public ResponseEntity<RespostaHttp<Emprestimo>> getAllEmprestimosAtivos() {
        List<Emprestimo> listaEmprestimos = emprestimoRepository.findAll(); // puxamos toda a lista de emprestimos do
                                                                            // repositorio

        try { // fazemos uma tratativa try/catch para capitura de erros na solicitação
            if (!listaEmprestimos.isEmpty()) {// primeiro verificamos se toda a lista está vazia ou nao
                // Aqui filtramos para que volte apenas os empréstimos ativos e links hateaos
                // filtramos para saber se o emprestimo é diferente de 1 (está cancelado)
                List<Emprestimo> emprestimosAtivos = listaEmprestimos.stream()
                        .filter(emprestimo -> emprestimo.getCancelado() != 1)
                        .peek(emprestimo -> emprestimo.add(linkTo(
                                methodOn(EmprestimoController.class).getEmprestimo(emprestimo.getCodEmprestimo()))
                                .withSelfRel()))
                        .collect(Collectors.toList());

                if (!emprestimosAtivos.isEmpty()) { // agora continuamos a verificar se os emprestimos ativos estão
                                                    // diferente de vazio
                    // retornamos uma resposta de sucesso, juntamente com a lista
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new RespostaHttp<>("Empréstimos encontrados.", emprestimosAtivos));
                } else { // senao, retornamos uma mensagem que a solicitação foi feita com sucesso, porém
                         // não foi encontrado nenhum empréstimo ativo.
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new RespostaHttp<>("Nenhum empréstimo ATIVO encontrado.", null));
                    // como nao temos nada na lista que esteja ativo, nao retornamos nada
                }
            } else { // vindo da primeira verificação, aqui retornamos que nenhum empréstimo foi
                     // encontrado, seja ele ativo ou não (cancelado)
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new RespostaHttp<>("Nenhum Empréstimo Encontrado.", null)); // nao retornamos nada
            }
        } catch (Exception e) { // se a tratativa não tiver sucesso, aplicamos a tratativa de erro e não
                                // retornamos nada da lista
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao procurar o empréstimo", null));
        }
    }// fim getAllEmprestimosAtivos

    /**
     * Método criado para procurar todos os emprestimos do sistema
     * e auxiliar na pesquisa de apenas um emprestimo
     * seja ele cancelado ou nao
     * 
     **/
    public ResponseEntity<RespostaHttp<Emprestimo>> getAllEmprestimos() {
        List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();

        try {
            if (!listaEmprestimo.isEmpty()) {
                for (Emprestimo emprestimo : listaEmprestimo) {
                    Long codEmprestimo = emprestimo.getCodEmprestimo();
                    emprestimo.add(linkTo(methodOn(EmprestimoController.class).getEmprestimo(codEmprestimo))
                            .withSelfRel());
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao procurar o empréstimo", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Nenhum Empréstimo Encontrado.", null));
    }

    /**
     * Método de criação/cadastro de um empréstimo
     * 
     * @param EmprestimoRecordDTO empretimoDTO - objeto a ser criado no sistema
     **/
    @PostMapping("/emprestimos")
    public ResponseEntity<Emprestimo> createEmprestimo(EmprestimoRecordDTO emprestimoDto) {

        try {
            if (emprestimoDto.codAluno() == null && emprestimoDto.codCurso() == null && emprestimoDto.codLivro() == null
                    && emprestimoDto.codRespEmprestimo() == null && emprestimoDto.dataDevolucao() == null) {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
}
