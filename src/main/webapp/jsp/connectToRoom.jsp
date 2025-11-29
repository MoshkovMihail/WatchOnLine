<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/connectToRoom.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Подключение к комнате</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="connect-page">
        <section class="join-card">
            <h1>Присоединиться к комнате</h1>
            <p class="join-subtitle">
                Введите ID комнаты, который вам прислал создатель.
            </p>

            <c:if test="${not empty error_message}">
                <div class="error">
                    <c:out value="${error_message}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/connectToRoom"
                  method="post"
                  class="join-form">

                <label for="room_id" class="join-label">ID комнаты</label>

                <input type="text"
                       id="room_id"
                       name="room_id"
                       class="join-input"
                       placeholder="например, 1"/>

                <button type="submit" class="btn">
                    Присоединиться
                </button>
            </form>
        </section>
    </main>
</body>
</html>
