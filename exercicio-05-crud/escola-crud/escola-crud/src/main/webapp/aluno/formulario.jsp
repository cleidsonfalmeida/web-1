<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'pt_BR'}" />
<fmt:setBundle basename="i18n/messages" />
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="aluno.formulario.titulo" /></title>
</head>
<body>
    <h1><fmt:message key="aluno.formulario.titulo" /></h1>

    <p>
        <a href="AlunoController?action=novo&lang=pt_BR">Português</a> |
        <a href="AlunoController?action=novo&lang=en_US">English</a>
    </p>

    <form action="AlunoController" method="get">
        <input type="hidden" name="action" value="${aluno != null ? 'atualizar' : 'inserir'}" />
        <c:if test="${aluno != null}">
            <input type="hidden" name="id" value="${aluno.id}" />
        </c:if>

        <label><fmt:message key="aluno.nome" />: </label>
        <input type="text" name="nome" value="${aluno != null ? aluno.nome : ''}" required /><br/>

        <label><fmt:message key="aluno.email" />: </label>
        <input type="email" name="email" value="${aluno != null ? aluno.email : ''}" required /><br/>

        <label><fmt:message key="aluno.matricula" />: </label>
        <input type="text" name="matricula" value="${aluno != null ? aluno.matricula : ''}" required /><br/>

        <label><fmt:message key="aluno.idade" />: </label>
        <input type="number" name="idade" value="${aluno != null ? aluno.idade : ''}" required /><br/>

        <label><fmt:message key="aluno.curso" />: </label>
        <select name="curso" required>
            <c:forEach var="curso" items="${listaCursos}">
                <option value="${curso.id}" <c:if test="${aluno != null && aluno.curso.id == curso.id}">selected</c:if>>
                    ${curso.nome}
                </option>
            </c:forEach>
        </select><br/><br/>

        <input type="submit" value="<fmt:message key='salvar' />" />
    </form>

    <a href="AlunoController"><fmt:message key="voltar" /></a>
</body>
</html>
