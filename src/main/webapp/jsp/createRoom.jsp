<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/createRoom.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Новая комната</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="create-page">
        <section class="create-card">
            <h1>Создать комнату</h1>
            <p class="create-subtitle">
                Придумайте короткое и понятное название – его будут видеть все участники.
            </p>

            <c:if test="${not empty error_message}">
                <div class="error">
                    <c:out value="${error_message}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/createRoom"
                  method="post"
                  class="create-form">

                <label for="name" class="create-label">Название комнаты</label>

                <input type="text"
                       id="name"
                       name="name"
                       class="create-input"
                       placeholder="Например: Учёба, Проект, Семейные дела"/>

                <button type="submit" class="btn">
                    Создать комнату
                </button>
            </form>

            <p class="hint">
                После создания вы автоматически станете владельцем комнаты и сможете звать других.
            </p>
        </section>
    </main>
</body>
</html>
