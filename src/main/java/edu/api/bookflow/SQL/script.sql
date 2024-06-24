
CREATE TABLE aluno (
    cod_aluno serial PRIMARY KEY,
    ra TEXT NOT NULL,
    nome_completo TEXT NOT NULL,
    cpf TEXT UNIQUE NOT NULL,
    email TEXT UNIQUE NOT NULL,
    tel TEXT NOT NULL,
    tel_2 TEXT,
	endereco TEXT NOT NULL,
	cep TEXT NOT NULL,
    stts_ativo INTEGER DEFAULT 1
	
);

CREATE TABLE usuario_sistema (
    cod_usuario serial PRIMARY KEY,
    nome TEXT NOT NULL,
    sobrenome TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    senha TEXT NOT NULL,
    is_adm INTEGER DEFAULT 0,
    cpf TEXT UNIQUE NOT NULL,
    stts_ativo INTEGER DEFAULT 1
);

CREATE TABLE curso (
    cod_curso serial PRIMARY KEY,
    descricao TEXT NOT NULL,
    stts_ativo INTEGER DEFAULT 1
);

CREATE TABLE categoria (
    cod_categoria serial PRIMARY KEY,
    descricao TEXT NOT NULL,
	stts_ativo INTEGER DEFAULT 1
);

CREATE TABLE editora (
    cod_editora serial PRIMARY KEY,
    nome_fantasia TEXT NOT NULL,
    endereco TEXT,
    telefone TEXT,
    site TEXT,
    stts_ativo INTEGER DEFAULT 1
);

CREATE TABLE autor (
    cod_autor serial PRIMARY KEY,
    nome TEXT NOT NULL,
	stts_ativo INTEGER DEFAULT 1
);

CREATE TABLE livro (
    cod_livro serial PRIMARY KEY,
    titulo TEXT NOT NULL,
    isbn TEXT,
    patrimonio TEXT UNIQUE NOT NULL,
    cod_curso INTEGER NOT NULL,
    cod_categoria INTEGER NOT NULL,
    edicao INTEGER,
    ano INTEGER,
    descricao TEXT,
    cod_autor INTEGER NOT NULL,
    cod_editora INTEGER NOT NULL,
    stts_emprestado INTEGER DEFAULT 0,
    stts_ativo INTEGER DEFAULT 1,
    FOREIGN KEY(cod_curso) REFERENCES curso(cod_curso),
    FOREIGN KEY(cod_categoria) REFERENCES categoria(cod_categoria),
    FOREIGN KEY(cod_autor) REFERENCES autor(cod_autor),
    FOREIGN KEY(cod_editora) REFERENCES editora(cod_editora)
);

CREATE TABLE emprestimo (
    cod_emp serial PRIMARY KEY,
    cancelado INTEGER DEFAULT 0,
    cod_livro INTEGER NOT NULL,
    cod_aluno INTEGER NOT NULL,
    cod_curso INTEGER NOT NULL,
    resp_emp INTEGER NOT NULL,
    resp_dev INTEGER NOT NULL,
    data_emp DATE NOT NULL,
    data_dev DATE NOT NULL,
    atrasado INTEGER DEFAULT 0,
	observacao TEXT,
    FOREIGN KEY(cod_livro) REFERENCES livro(cod_livro),
    FOREIGN KEY(cod_aluno) REFERENCES aluno(cod_aluno),
    FOREIGN KEY(cod_curso) REFERENCES curso(cod_curso),
    FOREIGN KEY(resp_emp) REFERENCES usuario_sistema(cod_usuario),
    FOREIGN KEY(resp_dev) REFERENCES usuario_sistema(cod_usuario)
);
