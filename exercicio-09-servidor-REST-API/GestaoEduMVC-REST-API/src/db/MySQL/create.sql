DROP DATABASE IF EXISTS GestaoEdu;
CREATE DATABASE GestaoEdu;
USE GestaoEdu;

CREATE TABLE aluno (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(100),
    data_nascimento DATE
);

CREATE TABLE disciplina (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    codigo VARCHAR(10) UNIQUE NOT NULL,
    professor_responsavel VARCHAR(100)
);

CREATE TABLE matricula (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT UNSIGNED NOT NULL,
    disciplina_id INT UNSIGNED NOT NULL,
    
    CONSTRAINT fk_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id) ON DELETE CASCADE,
    CONSTRAINT fk_disciplina FOREIGN KEY (disciplina_id) REFERENCES disciplina(id) ON DELETE CASCADE,
    CONSTRAINT uq_matricula UNIQUE (aluno_id, disciplina_id)
);

INSERT INTO aluno (nome, cpf, matricula, email, data_nascimento) VALUES
('Alice Silva', '123.456.789-00', 'MAT001', 'alice@example.com', '2005/01/15'),
('Bruno Costa', '234.567.890-11', 'MAT002', 'bruno@example.com', '2004/03/22'),
('Carla Mendes', '345.678.901-22', 'MAT003', 'carla@example.com', '2005/07/11'),
('Diego Lima', '456.789.012-33', 'MAT004', 'diego@example.com', '2003/09/30'),
('Eduarda Souza', '567.890.123-44', 'MAT005', 'eduarda@example.com', '2005/05/19'),
('Felipe Torres', '678.901.234-55', 'MAT006', 'felipe@example.com', '2004/12/02'),
('Gabriela Rocha', '789.012.345-66', 'MAT007', 'gabriela@example.com', '2005/10/10'),
('Henrique Dias', '890.123.456-77', 'MAT008', 'henrique@example.com', '2003/06/27'),
('Isabela Martins', '901.234.567-88', 'MAT009', 'isabela@example.com', '2004/11/05'),
('João Pereira', '012.345.678-99', 'MAT010', 'joao@example.com', '2005/02/08');


INSERT INTO disciplina (nome, codigo, professor_responsavel) VALUES
('Matemática Básica', 'D001', 'Prof. Ana Paula'),
('Física I', 'D002', 'Prof. Carlos Alberto'),
('Química Geral', 'D003', 'Prof. Fernanda Lima'),
('Biologia Celular', 'D004', 'Prof. Júlio César'),
('História Geral', 'D005', 'Prof. Marta Vieira'),
('Geografia Física', 'D006', 'Prof. Leandro Silva'),
('Literatura Brasileira', 'D007', 'Prof. Regina Souza'),
('Gramática e Redação', 'D008', 'Prof. Tiago Nunes'),
('Inglês Básico', 'D009', 'Prof. Amanda Cruz'),
('Espanhol Básico', 'D010', 'Prof. Raul Jimenez'),
('Educação Física', 'D011', 'Prof. Patricia Ramos'),
('Filosofia', 'D012', 'Prof. Ricardo Borges'),
('Sociologia', 'D013', 'Prof. Vanessa Matos'),
('Artes Visuais', 'D014', 'Prof. Bruno Freitas'),
('Informática Básica', 'D015', 'Prof. Carla Moura'),
('Programação I', 'D016', 'Prof. Daniel Souza'),
('Banco de Dados', 'D017', 'Prof. Marina Lima'),
('Redes de Computadores', 'D018', 'Prof. Sérgio Araújo'),
('Projeto Interdisciplinar', 'D019', 'Prof. Elisa Gomes'),
('Empreendedorismo', 'D020', 'Prof. Rafael Aurélio');


INSERT INTO matricula (aluno_id, disciplina_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 5), (2, 6), (2, 7), (2, 8),
(3, 9), (3, 10), (3, 11), (3, 12),
(4, 13), (4, 14), (4, 15), (4, 16),
(5, 17), (5, 18), (5, 19), (5, 20),
(6, 1), (6, 5), (6, 9), (6, 13),
(7, 2), (7, 6), (7, 10), (7, 14),
(8, 3), (8, 7), (8, 11), (8, 15),
(9, 4), (9, 8), (9, 12), (9, 16),
(10, 1), (10, 17), (10, 18), (10, 20);
