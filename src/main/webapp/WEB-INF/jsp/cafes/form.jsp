<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose>
            <c:when test="${empty cafe.id}">Novo cafe</c:when>
            <c:otherwise>Editar cafe</c:otherwise>
        </c:choose>
        - MVC Aula
    </title>
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
        <h1>
            <c:choose>
                <c:when test="${empty cafe.id}">Novo cafe</c:when>
                <c:otherwise>Editar cafe</c:otherwise>
            </c:choose>
        </h1>
    </div>

    <div class="card">
        <c:if test="${not empty erro}">
            <div class="alert alert-erro">${erro}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/cafes">
            <input type="hidden" name="acao" value="salvar">
            <input type="hidden" name="id" value="${cafe.id}">

            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" value="${cafe.nome}">
            </div>

            <div class="form-group">
                <label for="endereco">Endereco</label>
                <input type="text" id="endereco" name="endereco" value="${cafe.endereco}">
            </div>

            <div class="form-group">
                <label for="bairro">Bairro</label>
                <input type="text" id="bairro" name="bairro" value="${cafe.bairro}">
            </div>

            <div class="form-group">
                <label for="cafePrincipal">Cafe principal</label>
                <input type="text" id="cafePrincipal" name="cafePrincipal" value="${cafe.cafePrincipal}">
            </div>

            <div class="actions">
                <button type="submit" class="btn">Salvar</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/cafes">Cancelar</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>
