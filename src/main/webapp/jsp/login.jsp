<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/registration.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

<form action="${pageContext.request.contextPath}/login" method="post" id="loginForm">
    <div class="form-group">
        <input type="text" id="login" name="login" placeholder="Логин">
        <div class="error" id="loginError"></div>
    </div>

    <div class="form-group">
        <input type="password" id="password" name="password" placeholder="Пароль">
        <div class="error" id="passwordError"></div>
    </div>
    <c:if test="${not empty error_message}">
        <div class="error">${error_message}</div>
    </c:if>

    <button type="submit">войти</button>
</form>

<div class="success" id="successMessage"></div>


</body>
</html>