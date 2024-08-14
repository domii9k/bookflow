package edu.api.bookflow.Model;

import java.util.List;
import lombok.Data;

/**
 * Classe RespostaHttp para retorno de respostas dentro do body
 * junto ao conteudo esperado no retorno da requisição
 * 
 */
@Data // Com a utilização desta anotação, não será
      // preciso gerar os getters e ‘setters’ da classe
public class RespostaHttp<T> {

    private String resposta;
    private List<T> lista;

    /**
     * @param String resposta, List<T> lista
     * Método construtor que irá passar a mensagem
     **/
    public RespostaHttp(String resposta, List<T> lista) {
        this.resposta = resposta;
        this.lista = lista;
    }
}
