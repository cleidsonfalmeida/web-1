<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}" />
<fmt:setBundle basename="i18n.messages" />

<html>
<head>
  <title><fmt:message key="resultado" /></title>
</head>
<body>
  <h1><fmt:message key="resultado" /></h1>

  <p>
    <fmt:message key="${mensagemKey}">
      <fmt:param value="${valorEntrada}" />
      <fmt:param value="${valorConvertido}" />
    </fmt:message>
  </p>

  <a href="index.jsp?lang=${param.lang}">
    <fmt:message key="erro.voltar" />
  </a>
</body>
</html>
