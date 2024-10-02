
CREATE DATABASE bookflowdb;


-- autor definição

-- DROP TABLE autor;

CREATE TABLE autor (
	cod_autor bigserial NOT NULL,
	nome text NOT NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT autor_pkey PRIMARY KEY (cod_autor)
);

-- categoria definição

-- DROP TABLE categoria;

CREATE TABLE categoria (
	cod_categoria bigserial NOT NULL,
	descricao text NOT NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (cod_categoria)
);

-- curso definição

-- DROP TABLE curso;

CREATE TABLE curso (
	cod_curso bigserial NOT NULL,
	descricao text NOT NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT curso_pkey PRIMARY KEY (cod_curso)
);


-- editora definição

-- DROP TABLE editora;

CREATE TABLE editora (
	cod_editora bigserial NOT NULL,
	nome_fantasia text NOT NULL,
	endereco text NULL,
	telefone text NULL,
	site text NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT editora_pkey PRIMARY KEY (cod_editora)
);

-- aluno definição

-- DROP TABLE aluno;

CREATE TABLE aluno (
	cod_aluno bigserial NOT NULL,
	ra text NOT NULL,
	nome_completo text NOT NULL,
	cpf text NOT NULL,
	email text NOT NULL,
	tel text NOT NULL,
	tel_2 text NULL,
	endereco text NOT NULL,
	cep text NOT NULL,
	cod_curso int4 NOT NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT aluno_cpf_key UNIQUE (cpf),
	CONSTRAINT aluno_email_key UNIQUE (email),
	CONSTRAINT aluno_pkey PRIMARY KEY (cod_aluno)
);

-- aluno chaves estrangeiras

ALTER TABLE aluno ADD CONSTRAINT aluno_cod_curso_fkey FOREIGN KEY (cod_curso) REFERENCES curso(cod_curso);


-- usuario_sistema definição

-- DROP TABLE usuario_sistema;

CREATE TABLE usuario_sistema (
	cod_usuario bigserial NOT NULL,
	nome text NOT NULL,
	sobrenome text NOT NULL,
	email text NOT NULL,
	senha text NOT NULL,
	cpf text NOT NULL,
	stts_ativo bool DEFAULT true NULL,
	permissao text DEFAULT 'BIBLIOTECARIO'::text NULL,
	CONSTRAINT usuario_sistema_cpf_key UNIQUE (cpf),
	CONSTRAINT usuario_sistema_email_key UNIQUE (email),
	CONSTRAINT usuario_sistema_pkey PRIMARY KEY (cod_usuario)
);

-- livro definição

-- DROP TABLE livro;

CREATE TABLE livro (
	cod_livro bigserial NOT NULL,
	titulo text NOT NULL,
	isbn text NULL,
	patrimonio text NOT NULL,
	cod_curso int4 NOT NULL,
	edicao int4 NULL,
	ano int4 NULL,
	descricao text NULL,
	cod_autor int4 NOT NULL,
	cod_editora int4 NULL,
	stts_emprestado bool DEFAULT false NULL,
	stts_ativo bool DEFAULT true NULL,
	CONSTRAINT livro_patrimonio_key UNIQUE (patrimonio),
	CONSTRAINT livro_pkey PRIMARY KEY (cod_livro)
);

-- livro chaves estrangeiras

ALTER TABLE livro ADD CONSTRAINT livro_cod_autor_fkey FOREIGN KEY (cod_autor) REFERENCES autor(cod_autor);
ALTER TABLE livro ADD CONSTRAINT livro_cod_curso_fkey FOREIGN KEY (cod_curso) REFERENCES curso(cod_curso);
ALTER TABLE livro ADD CONSTRAINT livro_cod_editora_fkey FOREIGN KEY (cod_editora) REFERENCES editora(cod_editora);

-- categoria_livro definição

-- DROP TABLE categoria_livro;

CREATE TABLE categoria_livro (
	cod_clivro bigserial NOT NULL,
	cod_livro int4 NOT NULL,
	cod_categoria int4 NOT NULL,
	CONSTRAINT categoria_livro_pkey PRIMARY KEY (cod_clivro)
);

-- categoria_livro chaves estrangeiras

ALTER TABLE categoria_livro ADD CONSTRAINT categoria_livro_cod_categoria_fkey FOREIGN KEY (cod_categoria) REFERENCES categoria(cod_categoria);
ALTER TABLE categoria_livro ADD CONSTRAINT categoria_livro_cod_livro_fkey FOREIGN KEY (cod_livro) REFERENCES livro(cod_livro);

-- emprestimo definição

-- DROP TABLE emprestimo;

CREATE TABLE emprestimo (
	cod_emp bigserial NOT NULL,
	cod_livro int4 NOT NULL,
	cod_aluno int4 NOT NULL,
	resp_emp int4 NOT NULL,
	data_emp date DEFAULT CURRENT_DATE NULL,
	data_prevista_dev date NOT NULL,
	observacao text NULL,
	cancelado bool DEFAULT false NULL,
	atrasado bool DEFAULT false NULL,
	foi_devolvido bool DEFAULT false NULL,
	resp_dev int4 NULL,
	data_devolucao date NULL,
	CONSTRAINT emprestimo_pkey PRIMARY KEY (cod_emp)
);

-- emprestimo chaves estrangeiras

ALTER TABLE emprestimo ADD CONSTRAINT emprestimo_cod_aluno_fkey FOREIGN KEY (cod_aluno) REFERENCES aluno(cod_aluno);
ALTER TABLE emprestimo ADD CONSTRAINT emprestimo_cod_livro_fkey FOREIGN KEY (cod_livro) REFERENCES livro(cod_livro);
ALTER TABLE emprestimo ADD CONSTRAINT emprestimo_resp_dev_fkey FOREIGN KEY (resp_dev) REFERENCES usuario_sistema(cod_usuario);
ALTER TABLE emprestimo ADD CONSTRAINT emprestimo_resp_emp_fkey FOREIGN KEY (resp_emp) REFERENCES usuario_sistema(cod_usuario);