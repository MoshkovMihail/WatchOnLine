<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Регистрация</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/registration.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="auth-page">
        <section class="auth-card">
            <h1>Регистрация</h1>
            <p class="auth-subtitle">
                Создайте аккаунт, чтобы вести общие списки дел с друзьями, семьёй и командой.
            </p>

            <c:if test="${not empty error_message}">
                <div class="error global-error">
                    <c:out value="${error_message}"/>
                </div>
            </c:if>

            <form action="${pageContext.request.contextPath}/registration"
                  method="post"
                  id="regForm"
                  class="auth-form">

                <div class="form-group">
                    <label for="login">Логин</label>
                    <input type="text"
                           id="login"
                           name="login"
                           placeholder="Придумайте имя пользователя">
                    <div class="error" id="loginError"></div>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email"
                           id="email"
                           name="email"
                           placeholder="you@example.com">
                    <div class="error" id="emailError"></div>
                </div>

                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Минимум 6 символов">
                    <div class="error" id="passwordError"></div>
                </div>

                <button type="submit" class="btn auth-btn">
                    Зарегистрироваться
                </button>
            </form>

            <div class="auth-footer">
                <span>Уже есть аккаунт?</span>
                <a href="${pageContext.request.contextPath}/login" class="auth-link">
                    Войти
                </a>
            </div>

            <div class="success" id="successMessage"></div>
        </section>
    </main>
</body>
</html>
