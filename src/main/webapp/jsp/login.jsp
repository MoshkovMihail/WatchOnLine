<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Вход</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="auth-page">
        <section class="auth-card">
            <h1>Вход в аккаунт</h1>
            <p class="auth-subtitle">Введите логин и пароль, чтобы продолжить.</p>

            <c:if test="${not empty error_message}">
                <div class="error global-error">
                    <c:out value="${error_message}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/login"
                  method="post"
                  id="loginForm">

                <div class="form-group">
                    <label for="login">Логин</label>
                    <input type="text"
                           id="login"
                           name="login"
                           placeholder="Ваш логин">
                    <div class="error" id="loginError"></div>
                </div>

                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Ваш пароль">
                    <div class="error" id="passwordError"></div>
                </div>

                <button type="submit" class="btn-primary">Войти</button>
            </form>

            <div class="success" id="successMessage"></div>

            <p class="auth-helper">
                Нет аккаунта?
                <a href="${pageContext.request.contextPath}/registration">
                    Зарегистрироваться
                </a>
            </p>
        </section>
    </main>
</body>
</html>
