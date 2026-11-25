<%--
  Redireciona a raiz da aplicacao para o home.
--%>
<%
    response.sendRedirect(request.getContextPath() + "/home");
%>
