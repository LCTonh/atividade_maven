<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <c:choose>
            <c:when test="${empty review.id}">Novo review</c:when>
            <c:otherwise>Editar review</c:otherwise>
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
            <a href="${pageContext.request.contextPath}/reviews">Cafes</a>
            <a href="${pageContext.request.contextPath}/reviews">Reviews</a>
        </nav>
    </div>
</header>

<main class="container">
    <div class="page-header">
        <h1>
            <c:choose>
                <c:when test="${empty review.id}">Nova review</c:when>
                <c:otherwise>Editar review</c:otherwise>
            </c:choose>
        </h1>
    </div>

    <div class="card">
        <c:if test="${not empty erro}">
            <div class="alert alert-erro">${erro}</div>
        </c:if>

        <form method="post" action="${pageContext.request.contextPath}/reviews">
            <input type="hidden" name="acao" value="salvar">
            <input type="hidden" name="id" value="${review.id}">

            <div class="form-group">
                <label for="cafeId">Café ID</label>
                <select id="cafeId" name="cafeId">
                    <option value="">-- Nenhum --</option>
                    <c:forEach var="idCafe" items="${cafe_ids}">
                        <option value="${idCafe}" ${review.cafeId == idCafe ? 'selected' : ''}>
                            ${idCafe}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="autor">Autor</label>
                <input type="text" id="autor" name="autor" value="${review.autor}">
            </div>

            <div class="form-group">
                <label for="notaBebida">Nota da Bebida</label>
                <input type="number" id="notaBebida" name="notaBebida" value="${review.notaBebida}" min="0" max="10">
            </div>

            <div class="form-group">
                <label for="notaAmbiente">Nota do Ambiente</label>
                <input type="number" id="notaAmbiente" name="notaAmbiente" value="${review.notaAmbiente}" min="0" max="10">
            </div>

            <div class="form-group">
                <label for="notaAtendimento">Nota do Atendimento</label>
                <input type="number" id="notaAtendimento" name="notaAtendimento" value="${review.notaAtendimento}" min="0" max="10">
            </div>

            <div class="form-group">
                <label for="comentario">Comentário</label>
                <input type="text" id="comentario" name="comentario" value="${review.comentario}">
            </div>

            <div class="form-group">
                <label for="criadoEm">Criado em</label>
                <input type="datetime-local" id="criadoEm" name="criadoEm" value="${review.criadoEm}">
            </div>

            <div class="actions">
                <button type="submit" class="btn">Salvar</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/reviews">Cancelar</a>
            </div>
        </form>
    </div>
</main>
</body>
</html>
