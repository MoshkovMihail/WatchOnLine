<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>
    <h1>Мои комнаты</h1>

    <c:if test="${empty rooms}">
        <p>У вас пока нет комнат.</p>
    </c:if>

    <c:if test="${not empty rooms}">
        <ul>
            <c:forEach var="room" items="${rooms}">
                <li>
                    <a href="${pageContext.request.contextPath}/room?id=${room.id}">
                        <c:out value="${room.name}"/>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </c:if>

</body>

</html>