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
