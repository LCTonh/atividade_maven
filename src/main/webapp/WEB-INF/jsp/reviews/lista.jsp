<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Reviews - MVC Aula</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
<header class="topbar">
    <div class="container">
        <strong>MVC Aula</strong>
        <nav>
            <a href="${pageContext.request.contextPath}/home">Home</a>
            <a href="${pageContext.request.contextPath}/cafes">Cafes</a>
            <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
        </nav>
    </div>
</header>

<main class="container">
    <div class="page-header">
        <h1>Reviews</h1>
        <a class="btn" href="${pageContext.request.contextPath}/reviews?acao=novo">Nova review</a>
    </div>

    <c:if test="${not empty erro}">
        <div class="alert alert-erro">${erro}</div>
    </c:if>

    <div class="table-wrap">
        <c:choose>
            <c:when test="${empty reviews}">
                <p class="empty">Nenhuma review cadastrada.</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Cafe ID</th>
                        <th>Autor</th>
                        <th>Nota Bebida</th>
                        <th>Nota Ambiente</th>
                        <th>Nota Atendimento</th>
                        <th>Comentario</th>
                        <th>Criado Em</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="review" items="${reviews}">
                        <tr>
                            <td>${review.id}</td>
                            <td>${review.cafeId}</td>
                            <td>${review.autor}</td>
                            <td>${review.notaBebida}</td>
                            <td>${review.notaAmbiente}</td>
                            <td>${review.notaAtendimento}</td>
                            <td>${review.comentario}</td>
                            <td>${review.criadoEm}</td>
                            <td class="links">
                                <a href="${pageContext.request.contextPath}/reviews?acao=editar&id=${review.id}">Editar</a>
                                <a href="${pageContext.request.contextPath}/reviews?acao=excluir&id=${review.id}"
                                    onclick="return confirm('Excluir esta review?');">Excluir</a>
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
