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

    <form action="${pageContext.request.contextPath}/connectToRoom"
          class="join"
          method="post"
          id="regForm">

        <h2>Присоединиться к комнате</h2>

        <label for="room_id"><p>Введите ID комнаты</p></label>

        <c:if test="${not empty error_message}">
            <div class="error">
                <c:out value="${error_message}"/>
            </div>
        </c:if>

        <input type="text"
               id="room_id"
               name="room_id"
               placeholder="например, 1"/>

        <button type="submit">Присоединиться к комнате</button>
    </form>
</body>
</html>