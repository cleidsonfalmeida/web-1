package com.escola.controller;

import com.escola.dao.AlunoDAO;
import com.escola.dao.CursoDAO;
import com.escola.modelo.Aluno;
import com.escola.modelo.Curso;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/AlunoController")
public class AlunoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private AlunoDAO alunoDAO;
    private CursoDAO cursoDAO;

    @Override
    public void init() throws ServletException {
        alunoDAO = new AlunoDAO();
        cursoDAO = new CursoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        if (lang != null) {
            request.getSession().setAttribute("lang", lang);
        }
        String acao = request.getParameter("action");
        if (acao == null) {
            acao = "listar";
        }

        switch (acao) {
            case "novo":
                mostrarFormulario(request, response);
                break;
            case "inserir":
                inserirAluno(request, response);
                break;
            case "deletar":
                deletarAluno(request, response);
                break;
            case "editar":
                mostrarFormularioEdicao(request, response);
                break;
            case "atualizar":
                atualizarAluno(request, response);
                break;
            default:
                listarAlunos(request, response);
                break;
        }
    }

    private void listarAlunos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Aluno> lista = alunoDAO.listarTodos();
        request.setAttribute("listaAlunos", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("aluno/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Curso> listaCursos = cursoDAO.listarTodos();
        request.setAttribute("listaCursos", listaCursos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("aluno/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void inserirAluno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String matricula = request.getParameter("matricula");
        int idade = Integer.parseInt(request.getParameter("idade"));
        int cursoId = Integer.parseInt(request.getParameter("curso"));

        Curso curso = new Curso();
        curso.setId(cursoId);

        Aluno aluno = new Aluno(0, nome, email, matricula, idade, curso);
        alunoDAO.salvar(aluno);
        response.sendRedirect("AlunoController");
    }

    private void deletarAluno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        alunoDAO.remover(id);
        response.sendRedirect("AlunoController");
    }

    private void mostrarFormularioEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Aluno alunoExistente = alunoDAO.buscarPorId(id);
        List<Curso> listaCursos = cursoDAO.listarTodos();
        request.setAttribute("aluno", alunoExistente);
        request.setAttribute("listaCursos", listaCursos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("aluno/formulario.jsp");
        dispatcher.forward(request, response);
    }

    private void atualizarAluno(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String matricula = request.getParameter("matricula");
        int idade = Integer.parseInt(request.getParameter("idade"));
        int cursoId = Integer.parseInt(request.getParameter("curso"));

        Curso curso = new Curso();
        curso.setId(cursoId);

        Aluno aluno = new Aluno(id, nome, email, matricula, idade, curso);
        alunoDAO.atualizar(aluno);
        response.sendRedirect("AlunoController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
