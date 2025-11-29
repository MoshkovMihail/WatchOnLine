<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Мои комнаты</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="rooms-page">
        <header class="rooms-header">
            <div>
                <h1 class="page-title">Мои комнаты</h1>
                <p class="page-subtitle">
                    Здесь отображаются все комнаты, в которых вы являетесь участником.
                </p>
            </div>

            <div class="rooms-actions">
                <a class="btn" href="${pageContext.request.contextPath}/createRoom">
                    Создать комнату
                </a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/connectToRoom">
                    Присоединиться по ID
                </a>
            </div>
        </header>

        <c:if test="${empty rooms}">
            <section class="empty-state">
                <p>У вас пока нет комнат.</p>
                <p class="empty-state-hint">
                    Нажмите «Создать комнату», чтобы пригласить друзей и вести списки задач вместе.
                </p>
            </section>
        </c:if>

        <c:if test="${not empty rooms}">
            <section class="rooms-grid">
                <c:forEach var="room" items="${rooms}">
                    <a class="room-card"
                       href="${pageContext.request.contextPath}/room?id=${room.id}">
                        <div class="room-card__name">
                            <c:out value="${room.name}"/>
                        </div>
                        <div class="room-card__meta">
                            Комната ID: <c:out value="${room.id}"/>
                        </div>
                    </a>
                </c:forEach>
            </section>
        </c:if>
    </main>

</body>
</html>
