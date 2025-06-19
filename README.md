## 📝 Instruções de execução do código (2025/1)

Este README aborda algumas instruções de execução do código do Projeto BookFlow (back-end api) [ATUALIZADO]. O front-end do projeto pode ser encontrado no repositório [Front End](https://github.com/domii9k/frontend_bookflow).

### ✔ Considerações:

1) O projeto foi removido do Render, devido à lentidão que a plataforma o coloca, por ser gratuito.
2) As novas instruções serão para rodar o projeto localmente.
3) Necessário criar o arquivo .env na raiz do projeto para criação da _Secret Key_.

### 🛠 Ferramentas
Para as requisições, foi utilizado o <i>Insomnia</i>, mas pode ser utilizado o “software” da sua preferência. Caso queira utilizar um navegador para as requisições GET, recomenda-se o Firefox, pois ele traz as requisições visivelmente mais atraentes.

## Banco de dados:

Este projeto utilizou o PostgreSQL para armazenamento dos dados. Será disponibilizado o SCRIPT do mesmo com algumas inserções prontas de exemplo.

Instale o software e crie o banco de acordo com o SCRIPT.

No arquivo _application.prorpeties_ substitua as variáveis para os dados do banco que criou.

## Executando Localmente:
Link da API do projeto: https://localhost:9000
> Essa rota sozinha não possui nenhuma página (no momento) e trará uma página de erro.\
> Agora, todas as requisições estão disponíveis para testes.

## 👩🏾‍💻 Autenticação de usuários

### Login
A implementação do _login_ na API foi feita com _Spring Security_ e o JWT. Para realizar o login é necessário inserir a url https://localhost:9000/auth/login.

Para o corpo, com a requisição do tipo **POST**:

````json
{
  "email": "algum_email",
  "senha": "alguma_senha"
}
````
>🚨 Necessário o usuário estar previamente cadastrado por um Administrador.

Após lançar a requisição, é necessário copiar o _**token**_ que será gerado para execução das próximas requisições. Antes de lançá-las, é preciso colar o token na aba de Autenticação do Insomnia (ou outro software), com o prefixo "**Bearer**".

### Cadastro de usuários

Somente um usuário Administrador pode realizar o cadastro de novos usuários.

URL: https://localhost:9000/auth/registro

CORPO:
````json
{
    "nome": "Nome",
    "sobrenome": "Sobrenome",
    "email": "email@gmail.com",
    "senha": "algumasenha",
    "permissao": "BIBLIOTECARIO",
    "cpf": "umcpf"
}
````

## ✔️ Requisições

#### 🔁 GET

No Insomnia, selecione a requisição GET e insira os seguintes links disponíveis para GET:
>https://localhost:9000/emprestimos \
>https://localhost:9000/cursos \
>https://localhost:9000/alunos \
>https://localhost:9000/livros \
>https://localhost:9000/usuarios
> 
>  Também há rotas para categorias, autores e editoras.

>🚨 Cada requisição possui headers para pesquisas na url. Você pode verificá-los [aqui](https://github.com/domii9k/bookflow/blob/main/orientacoes/PESQUISA_URL.md).

Nele, caso bem sucedido, será buscado e retornado todos os empréstimos, cursos, alunos, usuários ou livros já cadastrados no banco.
>Note que, ao voltar essas informações, outras informações sensíveis que, num projeto em produção, é altamente perigoso e proibido, também serão exibidas. Estes registros não incluem informações de pessoas reais, apenas informações testes. Novas configurações de segurança serão adicionadas ao longo do projeto.

Para GET individual (um item), basta inserir o mesmo link + /{id}. Exemplo: https://localhost:9000/emprestimos/23

#### ⬆ POST

No Insomnia, selecione a requisição POST e insira o link apresentado no GET + /novo. Exemplo:
> https://localhost:9000/emprestimos/novo

Para inserir um ‘item’, segue o JSON necessário para cadastrar um empréstimo. As inserções podem ser modificadas, desde que os itens existam no banco.

```json
{
  "codLivro": {
    "codLivro": 4
  },
  "aluno": {
    "codAluno": 9
  },
  "respEmprestimo": {
    "codUsuario": 3
  },
  "dataPrevDevolucao": "2024-09-30",
  "observacao": "Novo LALALALALAL"
}

```
>Outras variáveis não inseridas no JSON possuem valores DEFAULT, não sendo necessária a inserção. Como: a dataEmprestimo, cancelado, foiDevolvido, etc.

Caso bem sucedido, será retornado o emprestimo criado em formado JSON.
#### ⤴ PUT 
No Insomnia, selecione a opção PUT e insira o link + /{id}. Exemplo:
>https://localhost:9000/emprestimos/45 \
> Em "id" coloque o código que deseja alterar, desde que ele exista entre os registros.

Uma desvantagem do PUT é que será necessário inserir o corpo do empréstimo inteiro (id's das entidades relacionadas e outras variaveis) para funcionar. Caso contrário, será retornado apenas um erro 500.

```json
{
	"isCancelado": false,
	"codLivro": {
		"codLivro": 4
	},
	"aluno": {
		"codAluno": 9
	},
	"respEmprestimo": {
		"codUsuario": 3
	},
	"respDevolucao": {
		"codUsuario": 7
	},
	"dataEmprestimo": [
		2024,
		9,
		25
	],
	"dataPrevDevolucao": [
		2024,
		9,
		30
	],
	"dataDevolucao": [
		2024,
		9,
		25
	],
	"isAtrasado": false,
	"foiDevolvido": true,
	"observacao": "foi devolvido PUT"
}
```
Caso bem sucedido, será retornado o empréstimo alterado em formato JSON. Nada prático, certo? Temos uma solução!

#### 🖋️ PATCH

O PATCH é uma solução mais recomendável para grandes entidades, como o Empréstimo. Será necessário passar no corpo do JSON apenas o(s) campo(s) que deseja alterar. Como no PUT, informe o link e o ID do item.

````json
{
  "observacao": "este item foi alterado com o PATCH"
}
````
Caso bem sucedido, será retornado o corpo do JSON com o campo alterado.

#### ✖ DELETE
No Insomnia, selecione a opção de requisição DELETE e insira o link + /{id}. Exemplo:
>https://localhost:9000/emprestimos/4 \
> Em ID coloque o código que deseja deletar, desde que ele exista entre os registros.

Caso bem sucedido, será retornado o status 204 - _NO_CONTENT_

Pronto! Requisições realizadas com sucesso. \
Veja o README do front para informações sobre o mesmo no repositório [Front End](https://github.com/domii9k/frontend_bookflow). \
> 🚨 Para visualizar o corpo JSON de cada tabela para requisições de cadastro, clique _[aqui](https://github.com/domii9k/bookflow/blob/main/orientacoes/REQUISICOES.md)_.
 |


