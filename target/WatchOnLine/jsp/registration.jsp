<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"  language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/registration.js"></script>
</head>
<body>
    <jsp:include page="/jsp/navbar.jsp"/>

<form action="${pageContext.request.contextPath}/registration" method="post" id="regForm">

    <c:if test="${not empty error_message}">
        <div class="error">${error_message}</div>
    </c:if>

    <div class="form-group">
        <input type="text" id="login" name="login" placeholder="Логин">
        <div class="error" id="loginError"></div>

    </div>
    
    <div class="form-group">
        <input type="email" id="email" name="email" placeholder="Email">
        <div class="error" id="emailError"></div>
    </div>
    
    <div class="form-group">
        <input type="password" id="password" name="password" placeholder="Пароль">
        <div class="error" id="passwordError"></div>
    </div>
    
    <button type="submit">Зарегистрироваться</button>
</form>

<div class="success" id="successMessage"></div>


</body>
</html>