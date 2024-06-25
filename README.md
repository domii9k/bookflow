## 📝 Instruções de execução do código

Este README aborda algumas instruções de execução do código do Projeto BookFlow (back-end api). O front-end do projeto pode ser encontrado no repositório [Front End](https://github.com/domii9k/BookFlow-FrontEnd).

### ✔ Considerações:

1) O banco de dados deste projeto foi subido no [Supa Base](https://supabase.com/), mas o “script” do banco pode ser encontrado dentro do diretório do projeto, no pacote SQL.
2) O projeto em si está no [Render](https://render.com/), então não será necessário clonar o código em sua máquina local. Mas, caso queira, será necessário executar o Script no PostgreSQL e configurar as propriedades do projeto localmente.
> Como o deploy do projeto está no Render, pode acontecer das requisições demorarem um minuto ou mais, devido à inatividade que o site o coloca.
### 🛠 Ferramentas
Para as requisições, foi utilizado o PostMan, mas pode ser utilizado o “software” da sua preferência. Caso queira utilizar um navegador para as requisições GET, recomenda-se o Firefox, pois ele traz as requisições visivelmente mais atraentes.

## Executando Remotamente:
Link da API do projeto: https://bookflow-3gbn.onrender.com
> Essa rota sozinha não possui nenhuma página (no momento) e trará uma página de erro.\
>O projeto está em desenvolvimento, então algumas requisições podem não estar disponíveis. Por isso, este README irá ensinar apenas requisições da página de **Empréstimos** (GET, POST, PUT e DELETE). E, para as outras, apenas GET.

 #### 🔁 GET

No PostMan, selecione a requisição GET e insira os seguintes links disponíveis para GET:
>https://bookflow-3gbn.onrender.com/emprestimos \
>https://bookflow-3gbn.onrender.com/cursos \
>https://bookflow-3gbn.onrender.com/alunos \
>https://bookflow-3gbn.onrender.com/livros

Nele, caso bem sucedido, será buscado e retornado todos os empréstimos, cursos, alunos ou livros já cadastrados no banco.
>Note que, ao voltar essas informações, outras informações sensíveis que, num projeto em produção, é altamente perigoso e proibido, também serão exibidas. Estes registros não incluem informações de pessoas reais, apenas informações testes. Novas configurações de segurança serão adicionadas ao longo do projeto.

#### ⬆ POST

No PostMan, selecione a requisição POST e insira o seguinte link:
> https://bookflow-3gbn.onrender.com/emprestimos

Para inserir um ‘item’, segue o JSON necessário para cadastrar um empréstimo. As inserções podem ser modificadas, desde que os itens existam no banco.

```json

{
  "cancelado": 0,
  "codLivro": {
    "codLivro": 1
  },
  "codAluno": {
    "codAluno": 2
  },
  "codCurso": {
    "codCurso": 1
  },
  "codRespEmprestimo": {
    "codUsuario": 2
  },
  "dataEmprestimo": "2024-06-15",
  "dataDevolucao": "2024-06-30",
  "observacao": "observacao testeeeeeeeeeeeee",
  "atrasado": 0
}

```
>As variáveis _cancelado_ e _atrasado_ aceitam SOMENTE valores 0 ou 1.

Caso bem sucedido, será retornado o emprestimo criado em formado JSON.
#### ⤴ PUT 
No PostMan, selecione a opção PUT e insira o seguinte _link_:
>https://bookflow-3gbn.onrender.com/emprestimos/{codEmprestimo} \
> Em codEmprestimo coloque o código que deseja alterar, desde que ele exista entre os registros.

Insira o mesmo JSON, porém adicone a variavel do codigo do emprestimo:

```json
{
    "codEmprestimo":10,
    "cancelado": 1,
    "codLivro": {
        "codLivro": 1
    },
    "codAluno": {
        "codAluno": 2
    },
    "codCurso": {
        "codCurso": 1
    },
    "codRespEmprestimo": {
        "codUsuario": 2
    },
    "codRespDevolucao": {
        "codUsuario": 1
    },
    "dataEmprestimo": "2024-06-15",
    "dataDevolucao": "2024-06-30",
    "observacao": "observacao modificada",
    "atrasado": 0
}
```
Caso bem sucedido, será retornado o emprestimo alterado em formado JSON.
#### ✖ DELETE
No PostMan, selecione a opção de requisição DELETE e insira o seguinte link:
>https://bookflow-3gbn.onrender.com/emprestimos/{codEmprestimo} \
> Em codEmprestimo coloque o código que deseja deletar, desde que ele exista entre os registros.

Caso bem sucedido, deve aparecer a seguinte mensagem:

```json
{
    "resposta": "Empréstimo deletado com sucesso",
    "lista": null
}
```

Pronto! Requisições realizadas com sucesso. \
Veja o README do front para informações sobre o mesmo no repositório [Front End](https://github.com/domii9k/BookFlow-FrontEnd).