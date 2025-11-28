<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/createRoom.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>
    <form action="${pageContext.request.contextPath}/createRoom" method="post" id="regForm">

        <label for="name"><p>Введите название комнаты</p></label>
        <c:if test="${not empty error_message}">
             <div class="error">${error_message}</div>
        </c:if>
        <input type="text" name = "name"/>
        <button type= "submit"> Создать комнату </button>
    </form>
<div id="clickMe">
        
</div>

</body>
</html>