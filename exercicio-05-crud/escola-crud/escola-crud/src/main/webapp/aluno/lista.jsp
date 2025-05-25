<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'pt_BR'}" />
<fmt:setBundle basename="i18n/messages" />
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="aluno.lista.titulo" /></title>
</head>
<body>
    <h1><fmt:message key="aluno.lista.titulo" /></h1>

    <p>
        <a href="AlunoController?action=listar&lang=pt_BR">Português</a> |
        <a href="AlunoController?action=listar&lang=en_US">English</a>
    </p>

    <a href="AlunoController?action=novo"><fmt:message key="aluno.novo" /></a>

    <table border="1">
        <tr>
            <th>ID</th>
            <th><fmt:message key="aluno.nome" /></th>
            <th><fmt:message key="aluno.email" /></th>
            <th><fmt:message key="aluno.matricula" /></th>
            <th><fmt:message key="aluno.idade" /></th>
            <th><fmt:message key="aluno.curso" /></th>
            <th><fmt:message key="acoes" /></th>
        </tr>
        <c:forEach var="aluno" items="${listaAlunos}">
            <tr>
                <td>${aluno.id}</td>
                <td>${aluno.nome}</td>
                <td>${aluno.email}</td>
                <td>${aluno.matricula}</td>
                <td>${aluno.idade}</td>
                <td>${aluno.curso.nome}</td>
                <td>
                    <a href="AlunoController?action=editar&id=${aluno.id}"><fmt:message key="editar" /></a> |
                    <a href="AlunoController?action=deletar&id=${aluno.id}"><fmt:message key="deletar" /></a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a href="${pageContext.request.contextPath}/index.html"><fmt:message key="voltar" /></a>

</body>
</html>
