CREATE DATABASE IF NOT EXISTS escola DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE escola;

CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL,
    idade INT NOT NULL,
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO curso (nome, carga_horaria) VALUES 
('Ciência da Computação', 4000),
('Engenharia', 3600),
('Administração', 3000);

INSERT INTO aluno (nome, email, matricula, idade, curso_id) VALUES
('Ana Silva', 'ana.silva@example.com', '2021001', 20, 1),
('Bruno Costa', 'bruno.costa@example.com', '2021002', 22, 2),
('Carla Mendes', 'carla.mendes@example.com', '2021003', 21, 3),
('Daniel Rocha', 'daniel.rocha@example.com', '2021004', 23, 1),
('Eduarda Lima', 'eduarda.lima@example.com', '2021005', 19, 2),
('Felipe Souza', 'felipe.souza@example.com', '2021006', 24, 3),
('Gabriela Pires', 'gabriela.pires@example.com', '2021007', 22, 1),
('Henrique Melo', 'henrique.melo@example.com', '2021008', 20, 2),
('Isabela Gomes', 'isabela.gomes@example.com', '2021009', 21, 3),
('João Pedro', 'joao.pedro@example.com', '2021010', 23, 1);
