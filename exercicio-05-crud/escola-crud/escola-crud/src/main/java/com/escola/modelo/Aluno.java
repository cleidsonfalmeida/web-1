package com.escola.modelo;

public class Aluno {
    private int id;
    private String nome;
    private String email;
    private String matricula;
    private int idade;
    private Curso curso; 

    public Aluno() {
    }

    public Aluno(int id, String nome, String email, String matricula, int idade, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
        this.idade = idade;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
