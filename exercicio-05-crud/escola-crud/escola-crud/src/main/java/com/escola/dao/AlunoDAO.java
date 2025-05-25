package com.escola.dao;

import com.escola.modelo.Aluno;
import com.escola.modelo.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/escola?useSSL=false";
    private String jdbcUsuario = "root";
    private String jdbcSenha = "root";

    private static final String INSERIR_SQL = "INSERT INTO aluno (nome, email, matricula, idade, curso_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECIONAR_POR_ID = "SELECT a.*, c.nome as nome_curso, c.carga_horaria FROM aluno a LEFT JOIN curso c ON a.curso_id = c.id WHERE a.id = ?";
    private static final String SELECIONAR_TODOS = "SELECT a.*, c.nome as nome_curso, c.carga_horaria FROM aluno a LEFT JOIN curso c ON a.curso_id = c.id";
    private static final String DELETAR_SQL = "DELETE FROM aluno WHERE id = ?";
    private static final String ATUALIZAR_SQL = "UPDATE aluno SET nome = ?, email = ?, matricula = ?, idade = ?, curso_id = ? WHERE id = ?";

    public void salvar(Aluno aluno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);
                 PreparedStatement stmt = conexao.prepareStatement(INSERIR_SQL)) {
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getEmail());
                stmt.setString(3, aluno.getMatricula());
                stmt.setInt(4, aluno.getIdade());
                stmt.setInt(5, aluno.getCurso().getId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Aluno buscarPorId(int id) {
        Aluno aluno = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);
                 PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_POR_ID)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String matricula = rs.getString("matricula");
                    int idade = rs.getInt("idade");
                    int cursoId = rs.getInt("curso_id");
                    String nomeCurso = rs.getString("nome_curso");
                    int cargaHoraria = rs.getInt("carga_horaria");

                    Curso curso = new Curso(cursoId, nomeCurso, cargaHoraria);
                    aluno = new Aluno(id, nome, email, matricula, idade, curso);
                }
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);
                 PreparedStatement stmt = conexao.prepareStatement(SELECIONAR_TODOS);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String matricula = rs.getString("matricula");
                    int idade = rs.getInt("idade");
                    int cursoId = rs.getInt("curso_id");
                    String nomeCurso = rs.getString("nome_curso");
                    int cargaHoraria = rs.getInt("carga_horaria");

                    Curso curso = new Curso(cursoId, nomeCurso, cargaHoraria);
                    Aluno aluno = new Aluno(id, nome, email, matricula, idade, curso);
                    alunos.add(aluno);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public void atualizar(Aluno aluno) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);
                 PreparedStatement stmt = conexao.prepareStatement(ATUALIZAR_SQL)) {
                stmt.setString(1, aluno.getNome());
                stmt.setString(2, aluno.getEmail());
                stmt.setString(3, aluno.getMatricula());
                stmt.setInt(4, aluno.getIdade());
                stmt.setInt(5, aluno.getCurso().getId());
                stmt.setInt(6, aluno.getId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);
                 PreparedStatement stmt = conexao.prepareStatement(DELETAR_SQL)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
