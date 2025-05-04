package br.ufscar.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DistanceConverterServlet", urlPatterns = {"/convert"})
public class DistanceConverterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processar(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processar(request, response);
    }

    private void processar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String valorStr = request.getParameter("value");
        String opcao = request.getParameter("option");

        try {
            if (valorStr == null || opcao == null) {
                throw new IllegalArgumentException("Parâmetros inválidos");
            }

            double entrada = Double.parseDouble(valorStr);
            double convertido;
            String mensagemKey;

            switch (opcao) {
                case "mi-m":
                    convertido = entrada * 1609.34;
                    mensagemKey = "mi-m";
                    break;
                case "m-mi":
                    convertido = entrada / 1609.34;
                    mensagemKey = "m-mi";
                    break;
                case "ft-m":
                    convertido = entrada * 0.3048;
                    mensagemKey = "ft-m";
                    break;
                case "m-ft":
                    convertido = entrada / 0.3048;
                    mensagemKey = "m-ft";
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida");
            }

            String valorEntradaFormatado = String.format("%.2f", entrada);
            String valorConvertidoFormatado = String.format("%.2f", convertido);

            request.setAttribute("valorEntrada", valorEntradaFormatado);
            request.setAttribute("valorConvertido", valorConvertidoFormatado);
            request.setAttribute("mensagemKey", mensagemKey);

            RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
        }
    }
}
