package com.escola.dao;

import com.escola.modelo.Curso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/escola?useSSL=false";
    private String jdbcUsuario = "root";   
    private String jdbcSenha = "root";     

    private static final String SELECT_TODOS_CURSOS = "SELECT id, nome, carga_horaria FROM curso";

    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexao = DriverManager.getConnection(jdbcURL, jdbcUsuario, jdbcSenha);

            PreparedStatement statement = conexao.prepareStatement(SELECT_TODOS_CURSOS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int cargaHoraria = resultSet.getInt("carga_horaria");

                Curso curso = new Curso(id, nome, cargaHoraria);
                cursos.add(curso);
            }
            System.out.println("Total cursos encontrados: " + cursos.size());

            resultSet.close();
            statement.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }
}
