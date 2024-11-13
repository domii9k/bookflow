### Requisições de cadastro das tabelas (JSON)

#### 👥 Usuários

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
> 🚨 Todos os dados são obrigatórios.

#### 👧🏾 Alunos

````json
{
  "nomeCompleto": "Roberta Lima dos Santos rrrrrrrr",
  "ra": "grsjytku",
  "cpf": "777.123.787-99",
  "codCurso": {
    "codCurso": 6
  },
  "email": "miuy.lima@gmail.com",
  "tel": "(77) 7777-1111",
  "tel2": "(77) 8888-9888",
  "cep": "78901-236",
  "endereco": "Rua GA, 902"
}
````
> 🚨 **tel2** não é obrigatório.

#### 📚 Livro

````json
{
  "titulo": "Teste Livro 245678",
  "isbn": "971-0-11-237764-0",
  "patrimonio": "PAT07084",
  "codCurso": {
    "codCurso": 4
  },
  "edicao": 5,
  "ano": 2018,
  "descricao": "descricao teste",
  "codAutor": {
    "codAutor": 1
  },
  "codEditora": {
    "codEditora": 2
  }
}
````
> 🚨 **descricao** e **edicao** não são obrigatórios.

