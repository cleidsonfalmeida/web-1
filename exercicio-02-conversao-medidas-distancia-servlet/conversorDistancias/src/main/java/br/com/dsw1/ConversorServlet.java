package br.com.dsw1;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ConversorServlet")
public class ConversorServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcao = request.getParameter("opcao");
        String valorStr = request.getParameter("valor");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (opcao == null || valorStr == null || valorStr.isEmpty()) {
                response.sendRedirect("erro.html");
                return;
            }

            double valorEntrada;
            try {
                valorEntrada = Double.parseDouble(valorStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("erro.html");
                return;
            }

            double valorConvertido;
            String mensagem = "";

            switch (opcao) {
                case "mi-m":
                    valorConvertido = valorEntrada * 1609.34;
                    mensagem = String.format("%.2f milhas equivalem a %.2f metros", valorEntrada, valorConvertido);
                    break;
                case "m-mi":
                    valorConvertido = valorEntrada / 1609.34;
                    mensagem = String.format("%.2f metros equivalem a %.2f milhas", valorEntrada, valorConvertido);
                    break;
                case "ft-m":
                    valorConvertido = valorEntrada * 0.3048;
                    mensagem = String.format("%.2f pés equivalem a %.2f metros", valorEntrada, valorConvertido);
                    break;
                case "m-ft":
                    valorConvertido = valorEntrada / 0.3048;
                    mensagem = String.format("%.2f metros equivalem a %.2f pés", valorEntrada, valorConvertido);
                    break;
                default:
                    response.sendRedirect("erro.html");
                    return;
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Resultado da Conversão</title></head>");
            out.println("<body>");
            out.println("<h1>" + mensagem + "</h1>");
            out.println("<a href='index.html'>Nova Conversão</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
