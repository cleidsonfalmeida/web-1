<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${param.lang != null ? param.lang : 'pt_BR'}" />
<fmt:setBundle basename="i18n.messages" />

<html>
<head>
  <title><fmt:message key="titulo" /></title>
</head>
<body>
  <!-- Seleção de idioma -->
  <form method="get" action="index.jsp">
    <select name="lang" onchange="this.form.submit()">
      <option value="pt_BR" ${param.lang == 'pt_BR' ? 'selected' : ''}>Português</option>
      <option value="en" ${param.lang == 'en' ? 'selected' : ''}>English</option>
    </select>
  </form>

  <h1><fmt:message key="titulo" /></h1>

  <!-- Formulário de conversão -->
  <form action="${pageContext.request.contextPath}/convert" method="post">
    <input type="hidden" name="lang" value="${param.lang}" />

    <label for="value"><fmt:message key="valor" /></label>
    <input type="text" id="valor" name="value" />

    <label for="option"><fmt:message key="opcao" /></label>
    <select id="option" name="option">
      <option value="mi-m"><fmt:message key="mi-m.option" /></option>
      <option value="m-mi"><fmt:message key="m-mi.option" /></option>
      <option value="ft-m"><fmt:message key="ft-m.option" /></option>
      <option value="m-ft"><fmt:message key="m-ft.option" /></option>
    </select>

    <button type="submit"><fmt:message key="converter" /></button>
  </form>
</body>
</html>
