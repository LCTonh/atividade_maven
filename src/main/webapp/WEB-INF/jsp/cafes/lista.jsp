<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perfis - MVC Aula</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
<header class="topbar">
    <div class="container">
        <strong>MVC Aula</strong>
        <nav>
            <a href="${pageContext.request.contextPath}/home">Home</a>
            <a href="${pageContext.request.contextPath}/cafes">Cafes</a>
            <a href="${pageContext.request.contextPath}/reviews">Reviews</a>\
        </nav>
    </div>
</header>

<main class="container">
    <div class="page-header">
        <h1>Cafes</h1>
        <a class="btn" href="${pageContext.request.contextPath}/cafes?acao=novo">Novo cafe</a>
    </div>

    <c:if test="${not empty erro}">
        <div class="alert alert-erro">${erro}</div>
    </c:if>

    <div class="table-wrap">
        <c:choose>
            <c:when test="${empty cafes}">
                <p class="empty">Nenhum Cafe cadastrado.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Endereco</th>
                        <th>Bairro</th>
                        <th>Cafe principal</th>
                        <th>Acoes</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cafe" items="${cafes}">
                        <tr>
                            <td>${cafe.id}</td>
                            <td>${cafe.nome}</td>
                            <td>${cafe.endereco}</td>
                            <td>${cafe.bairro}</td>
                            <td>${cafe.tipo_cafe_principal}</td>
                            <td class="links">
                                <a href="${pageContext.request.contextPath}/perfis?acao=editar&id=${cafe.id}">Editar</a>
                                <a href="${pageContext.request.contextPath}/perfis?acao=excluir&id=${cafe.id}"
                                    onclick="return confirm('Excluir este perfil?');">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
</html>
