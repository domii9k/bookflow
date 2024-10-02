INSERT INTO curso VALUES (1, 'Administração', true);
INSERT INTO curso VALUES (2, 'Estética', true);
INSERT INTO curso VALUES (3, 'Eventos', true);
INSERT INTO curso VALUES (4, 'Gastronomia', true);
INSERT INTO curso VALUES (5, 'Informática', true);
INSERT INTO curso VALUES (6, 'Programação de Jogos Digitais', true);
INSERT INTO curso VALUES (7, 'Logística', true);
INSERT INTO curso VALUES (8, 'Modelagem do Vestuário', true);
INSERT INTO curso VALUES (9, 'Multimídia', true);
INSERT INTO curso VALUES (10, 'Produção de Moda', true);
INSERT INTO curso VALUES (11, 'Rádio e Televisão', true);
INSERT INTO curso VALUES (12, 'Redes de Computadores', true);

INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (1, 'J. K. Rowling', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (2, 'George R. R. Martin', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (3, 'J. R. R. Tolkien', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (4, 'Agatha Christie', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (5, 'Isaac Asimov', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (6, 'Stephen King', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (7, 'Arthur Conan Doyle', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (8, 'Philip K. Dick', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (9, 'Dan Brown', true);
INSERT INTO autor (cod_autor, nome, stts_ativo) VALUES (10, 'H. G. Wells', true);

INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (1, 'Ficção', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (2, 'Fantasia', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (3, 'Mistério', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (4, 'Romance', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (5, 'Ciência', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (6, 'Biografia', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (7, 'Terror', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (8, 'História', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (9, 'Tecnologia', true);
INSERT INTO categoria (cod_categoria, descricao, stts_ativo) VALUES (10, 'Aventura', true);

INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (1, 'Editora Globo', 'Rua A, 123', '1111-1111', 'www.globo.com', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (2, 'Companhia das Letras', 'Rua B, 456', '2222-2222', 'www.companhiadasletras.com', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (3, 'Editora Rocco', 'Rua C, 789', '3333-3333', 'www.rocco.com.br', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (4, 'HarperCollins', 'Rua D, 101', '4444-4444', 'www.harpercollins.com', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (5, 'Editora Aleph', 'Rua E, 202', '5555-5555', 'www.aleph.com.br', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (6, 'Penguin Books', 'Rua F, 303', '6666-6666', 'www.penguinbooks.com', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (7, 'DarkSide Books', 'Rua G, 404', '7777-7777', 'www.darksidebooks.com', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (8, 'Editora Sextante', 'Rua H, 505', '8888-8888', 'www.sextante.com.br', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (9, 'Editora Intrínseca', 'Rua I, 606', '9999-9999', 'www.intrinseca.com.br', true);
INSERT INTO editora (cod_editora, nome_fantasia, endereco, telefone, site, stts_ativo) VALUES (10, 'Editora Record', 'Rua J, 707', '0000-0000', 'www.record.com.br', true);

INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (1, '2023001', 'Laura Silva', '123.456.789-00', 'laura@email.com', '99999-9999', 'Rua A, 123', '29100-000', 5, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (2, '2023002', 'João Souza', '234.567.890-11', 'joao@email.com', '98888-8888', 'Rua B, 456', '29101-000', 6, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (3, '2023003', 'Maria Oliveira', '345.678.901-22', 'maria@email.com', '97777-7777', 'Rua C, 789', '29102-000', 4, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (4, '2023004', 'Pedro Santos', '456.789.012-33', 'pedro@email.com', '96666-6666', 'Rua D, 101', '29103-000', 7, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (5, '2023005', 'Ana Costa', '567.890.123-44', 'ana@email.com', '95555-5555', 'Rua E, 202', '29104-000', 8, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (6, '2023006', 'Lucas Almeida', '678.901.234-55', 'lucas@email.com', '94444-4444', 'Rua F, 303', '29105-000', 9, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (7, '2023007', 'Carla Fernandes', '789.012.345-66', 'carla@email.com', '93333-3333', 'Rua G, 404', '29106-000', 10, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (8, '2023008', 'Paulo Correia', '890.123.456-77', 'paulo@email.com', '92222-2222', 'Rua H, 505', '29107-000', 11, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (9, '2023009', 'Marina Lima', '901.234.567-88', 'marina@email.com', '91111-1111', 'Rua I, 606', '29108-000', 12, true);
INSERT INTO aluno (cod_aluno, ra, nome_completo, cpf, email, tel, endereco, cep, cod_curso, stts_ativo) VALUES (10, '2023010', 'Roberto Nunes', '012.345.678-99', 'roberto@email.com', '90000-0000', 'Rua J, 707', '29109-000', 1, true);

INSERT INTO usuario_sistema VALUES (2, 'Beatriz', 'Lima', 'beatriz.lima@gmail.com', 'senha123', '234.234.234-23', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (3, 'Carlos', 'Silva', 'carlos.silva@gmail.com', 'senha123', '345.345.345-34', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (4, 'Diana', 'Souza', 'diana.souza@gmail.com', 'senha123', '456.456.456-45', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (5, 'Eduardo', 'Rocha', 'eduardo.rocha@gmail.com', 'senha123', '567.567.567-56', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (6, 'Fernanda', 'Moraes', 'fernanda.moraes@gmail.com', 'senha123', '678.678.678-67', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (7, 'Gustavo', 'Almeida', 'gustavo.almeida@gmail.com', 'senha123', '789.789.789-78', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (9, 'Igor', 'Pereira', 'igor.pereira@gmail.com', 'senha123', '901.901.901-90', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (10, 'Julia', 'Fernandes', 'julia.fernandes@gmail.com', 'senha123', '012.012.012-01', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (1, 'Alberto', 'Nunes', 'alberto.nunes@gmail.com', 'senha123', '123.123.123-12', true, 'ADMINISTRADOR');
INSERT INTO usuario_sistema VALUES (14, 'Jersonizio', 'Silva', 'jerson2@gmail.com', 'senha123', '1234566', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (8, 'Helena', 'Castro', 'helena.castro@gmail.com', 'senha123', '890.890.890-89', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (15, 'Alberto 2', 'Nunes', 'alberto2.nunes@gmail.com', 'senha123', '123.123.123-92', true, 'BIBLIOTECARIO');
INSERT INTO usuario_sistema VALUES (11, 'Jerson', 'Silva', 'jerson@gmail.com', 'senha123', '123456', false, 'BIBLIOTECARIO');

INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (1, 'Harry Potter e a Pedra Filosofal', 3, 1, 1997, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (2, 'A Guerra dos Tronos', 2, 2, 1996, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (3, 'O Senhor dos Anéis', 5, 3, 1954, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (4, 'Assassinato no Expresso Oriente', 1, 4, 1934, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (5, 'Fundação', 6, 5, 1951, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (6, 'It: A Coisa', 7, 6, 1986, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (7, 'Sherlock Holmes: O Cão dos Baskervilles', 8, 7, 1902, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (8, 'O Homem do Castelo Alto', 9, 8, 1962, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (9, 'O Código Da Vinci', 10, 9, 2003, false);
INSERT INTO livro (cod_livro, titulo, cod_editora, cod_autor, ano_publicacao, stts_emprestado) VALUES (10, 'A Máquina do Tempo', 4, 10, 1895, false);

INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (1, 1); -- Categoria de Fantasia para "Harry Potter e a Pedra Filosofal"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (2, 2); -- Categoria de Fantasia para "A Guerra dos Tronos"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (1, 3); -- Categoria de Fantasia para "O Senhor dos Anéis"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (3, 4); -- Categoria de Mistério para "Assassinato no Expresso Oriente"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (4, 5); -- Categoria de Ficção Científica para "Fundação"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (5, 6); -- Categoria de Terror para "It: A Coisa"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (3, 7); -- Categoria de Mistério para "Sherlock Holmes: O Cão dos Baskervilles"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (4, 8); -- Categoria de Ficção Científica para "O Homem do Castelo Alto"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (6, 9); -- Categoria de Suspense para "O Código Da Vinci"
INSERT INTO categoria_livro (cod_categoria, cod_livro) VALUES (4, 10); -- Categoria de Ficção Científica para "A Máquina do Tempo"






