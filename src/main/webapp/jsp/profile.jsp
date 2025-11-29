<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Профиль</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>

<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="profile-page">

        <!-- Верхние действия (выход + удалить) -->
        <section class="profile-actions">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button class="btn btn-secondary">Выйти из аккаунта</button>
            </form>

            <form action="${pageContext.request.contextPath}/deleteUser" method="post"
                  onsubmit="return confirm('Точно удалить аккаунт? Его потом уже не восстановить.');">
                <button class="btn btn-danger">
                    Удалить аккаунт навсегда
                </button>
            </form>
        </section>

        <!-- Основная карточка профиля -->
        <section class="profile-card">

            <header class="profile-card__header">
                <h1>Ваш аккаунт</h1>
            </header>

            <div class="profile-card__body">

                <!-- Аватар + загрузка -->
                <div class="profile-avatar-block">
                    <div class="avatar-wrapper">
                        <img src="${pageContext.request.contextPath}${user.avatarPath}" alt="Аватар пользователя"/>
                    </div>

                    <form action="${pageContext.request.contextPath}/uploadAvatar"
                          method="post"
                          enctype="multipart/form-data"
                          class="avatar-upload-form">

                        <label class="file-label">
                            <span>Выбрать файл</span>
                            <input type="file" name="avatar" class="file-input">
                        </label>

                        <button type="submit" class="btn btn-secondary">
                            Загрузить аватар
                        </button>
                    </form>
                </div>

                <!-- Текстовая информация -->
                <div class="profile-info-block">

                    <div class="profile-field">
                        <div class="profile-field__label">Имя пользователя</div>
                        <div class="profile-field__value">
                            <c:out value="${user.username}"/>
                        </div>
                    </div>

                    <div class="profile-field">
                        <div class="profile-field__label">Email</div>
                        <div class="profile-field__value">
                            <c:out value="${user.email}"/>
                        </div>
                    </div>

                    <!-- Сообщения об ошибке / успехе -->
                    <c:if test="${not empty error_message}">
                        <div class="error global-error">
                            <c:out value="${error_message}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty success_message}">
                        <div class="alert alert-success">
                            <c:out value="${success_message}"/>
                        </div>
                    </c:if>

                    <!-- Форма смены имени пользователя -->
                    <div class="profile-username-edit">
                        <h2>Изменить имя пользователя</h2>

                        <form action="${pageContext.request.contextPath}/updateUsername" method="post" class="username-form">
                            <label for="newUsername" class="profile-field__label">
                                Новое имя пользователя
                            </label>

                            <input type="text"
                                   id="newUsername"
                                   name="newUsername"
                                   value="${user.username}"
                                   class="input-text"/>

                            <button type="submit" class="btn">
                                Сохранить
                            </button>
                        </form>
                    </div>

                </div>
            </div>
        </section>
    </main>
</body>
</html>