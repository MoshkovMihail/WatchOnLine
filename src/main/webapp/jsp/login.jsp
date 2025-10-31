<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/login.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="../js/script.js"></script>
    <script src="../js/registration.js"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>

<form action="/login" method="post" id="loginForm">
    <div class="form-group">
        <input type="text" id="login" name="login" placeholder="Логин">
        <div class="error" id="loginError"></div>
    </div>

    <div class="form-group">
        <input type="password" id="password" name="password" placeholder="Пароль">
        <div class="error" id="passwordError"></div>
    </div>

    <button type="submit">войти</button>
</form>

<div class="success" id="successMessage"></div>


</body>
</html>