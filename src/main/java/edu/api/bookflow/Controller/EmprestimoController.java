package edu.api.bookflow.Controller;

import edu.api.bookflow.Repository.EmprestimoRepository;
import edu.api.bookflow.DTO.EmprestimoDTO;
import edu.api.bookflow.Model.Emprestimo;
import edu.api.bookflow.Model.RespostaHttp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

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
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro retornar o empréstimo");
        }

    }

    /**
     * Metodo para listar todos os empréstimos ativos do sistema (cancelado=0)
     * voltado para listar apenas os ativos na pagina de emprestimos
     **/
    @GetMapping
    public ResponseEntity<List<Emprestimo>> getAllEmprestimosAtivos() {
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
                                                    // diferentes de vazio
                    // retornamos uma resposta de sucesso, juntamente com a lista
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(emprestimosAtivos);
                } else { // senao, retornamos uma mensagem que a solicitação foi feita com sucesso, porém
                         // não foi encontrado nenhum empréstimo ativo.
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(null);
                    // como nao temos nada na lista que esteja ativo, nao retornamos nada
                }
            } else { // vindo da primeira verificação, aqui retornamos que nenhum empréstimo foi
                     // encontrado, seja ele ativo ou não (cancelado)
                return ResponseEntity.status(HttpStatus.OK)
                        .body(null); // nao retornamos nada
            }
        } catch (Exception e) { // se a tratativa não tiver sucesso, aplicamos a tratativa de erro e não
                                // retornamos nada da lista
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }// fim getAllEmprestimosAtivos

    /**
     * Método criado para procurar todos os emprestimos do sistema
     * e auxiliar na pesquisa de apenas um emprestimo
     * seja ele cancelado ou nao
     * 
     * @return Retorna um erro caso a tratativa indique que nao teve erro, mas que
     *         não hou emprestimo a ser encontrado
     **/
    public ResponseEntity<RespostaHttp<Emprestimo>> getAllEmprestimos() {
        List<Emprestimo> listaEmprestimo = emprestimoRepository.findAll();

        try {
            if (!listaEmprestimo.isEmpty()) {
                for (Emprestimo emprestimo : listaEmprestimo) {
                    Long codEmprestimo = emprestimo.getCodEmprestimo();
                    emprestimo.add(linkTo(methodOn(EmprestimoController.class).getEmprestimo(codEmprestimo))
                            .withSelfRel()); // aqui adicionamos um link para entrar em cada emprestimo especifico
                }
                // aqui não vamos retonar os emprestimos, ja que o bloco será usado apenas para
                // auxilio na busca de todos os empréstimos
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao procurar o empréstimo", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new RespostaHttp<>("Nenhum Empréstimo Encontrado.", null));
    }

    /**
     * Método de criação/cadastro de um empréstimo
     * 
     * @param EmprestimoDTO empretimoDTO - objeto a ser criado no sistema
     * @return Retorna o empréstimo criado em formato JSON
     **/
    @PostMapping
    public ResponseEntity<Object> createEmprestimo(@RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimo = new Emprestimo();
        try {
            BeanUtils.copyProperties(emprestimoDTO, emprestimo); // Com este método, podemos atribuir as propriedade da
                                                                 // classe DTO para a Model,
                                                                 // Sem precisarmos "setá-los" atributo por atributo
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao tentar cadastrar um emprestimo");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoRepository.save(emprestimo));
    }

    /**
     * Método para deletar um empréstimo:
     * Em suma, é fundamental que apenas este objeto seja passível de deleção,
     * visto que os outros podem depender de informações de outras tabelas e afete,
     * assim, o desempenho e segurança do sistema
     * 
     * @return Dependendo do comportamento da requisição, a api retornará três
     *         respostas diferentes
     **/
    @DeleteMapping("/{codEmprestimo}")
    public ResponseEntity<Object> deleteEmprestimo(@PathVariable Long codEmprestimo) {

        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(codEmprestimo);

        if (emprestimoOptional.isEmpty()) { //aqui verificamos se o ID solicitado existe
            //retorna um NOT_FOUND pois verifica que é verdadeiro (true) que o objeto nao existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RespostaHttp<>("Empréstimo não encontrado!", null));
        }

        try { //iniciamos o tratamento de erros na solicitação
            // como criamos um objeto acima (emprestimoOptional) para procurarmos pelo ID (codEmprestimo),
            Emprestimo emprestimo = emprestimoOptional.get(); //agora precisamos resgatar esse ID
            emprestimoRepository.delete(emprestimo); //depois podemos deletá-lo
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new RespostaHttp<>("Empréstimo deletado com sucesso", null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RespostaHttp<>("Erro ao deletar o empréstimo", null));
        }
    }

    /**
     * Método para atualizar os dados de um empréstimo
     * @param Long codEmprestimo - busca o emprestimo pelo ID
     * @param EmprestimoDTO emprestimoDTO - valida os dados e atribui os valores com a classe Emprestimo
     * **/
    @PutMapping("/{codEmprestimo}")
    public ResponseEntity<Object> atualizaEmprestimo(@PathVariable Long codEmprestimo,
            @RequestBody @Valid EmprestimoDTO emprestimoDTO) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(codEmprestimo);

        if (emprestimoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Empréstimo não encontrado!");
        }
        try {
            Emprestimo emprestimo = emprestimoOptional.get();
            BeanUtils.copyProperties(emprestimoDTO, emprestimo);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(emprestimoRepository.save(emprestimo));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar um Emprestimo");
        }

    }
}
