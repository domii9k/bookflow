# 📚 Sistema de Gerenciamento de Biblioteca

Este sistema permite gerenciar diversas entidades relacionadas a uma biblioteca, como empréstimos, alunos, usuários, livros, autores, categorias, cursos e editoras. A seguir, encontram-se as orientações para pesquisa e filtragem nas URLs das entidades.

## ✅ Orientações para pesquisas na URL das entidades (HEADERS)

### Empréstimos:

**1) Para filtrar os empréstimos, você pode usar os seguintes parâmetros:**

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (valor padrão = dataEmpréstimo)  |
| sortDir      | ASC ou DESC (ordenação crescente ou decrescente)               |
| cancelado    | true ou false                                                  |
| foiDevolvido | true ou false                                                  |
| atrasado     | true ou false (padrão = false)                                 |

### Alunos:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = nomeCompleto)          |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Usuários:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = codUsuario)            |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Categorias:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = descricao)             |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Autores:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = nome)                  |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Cursos:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = descricao)             |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Livros:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = titulo)                |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

### Editoras:

| **Header**   | **Descrição**                                                  |
|:-------------|:---------------------------------------------------------------|
| pag          | Número da página                                               |
| size         | Quantidade de itens por página                                 |
| sortBy       | Ordena pelo campo da entidade (padrão = nomeFantasia)          |
| sortDir      | ASC ou DESC                                                    |
| status       | true ou false                                                  |

---