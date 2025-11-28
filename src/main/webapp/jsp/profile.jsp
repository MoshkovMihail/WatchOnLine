<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>

<body>
    <jsp:include page="/jsp/header.jsp"/>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button>Выйти из аккаунта</button>
    </form>

    <form action="${pageContext.request.contextPath}/deleteUser" method="post"
        onsubmit="return confirm('Точно удалить аккаунт? его потом уже не востановить');">
        <button>Удалить аккаунт навсегда и без восстановления</button>
    </form>

    <div id="account">
        <p>Ваш аккаунт:</p>
    </div>

    <div class="avatar">
        <img src="${pageContext.request.contextPath}${user.avatarPath}" alt="Авы нету("/>
    </div>

    <form action="${pageContext.request.contextPath}/uploadAvatar"
          method="post"
          enctype="multipart/form-data">

        <input type="file" name="avatar">
        <button type="submit">Загрузить аватар</button>
    </form>

    <div class="username">
        <p>username:</p>
        <c:out value="${user.username}"/>
    </div>

    <div class="email">
        <p>email:</p>
        <p><c:out value="${user.email}"/></p>
    </div>

    <h2>Изменить имя пользователя</h2>

    <c:if test="${not empty error_message}">
        <div class="error">
            <c:out value="${error_message}"/>
        </div>
    </c:if>

    <c:if test="${not empty success_message}">
        <div class="success">
            <c:out value="${success_message}"/>
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/updateUsername" method="post">
        <label for="newUsername">Новое имя пользователя:</label>
        <input type="text"
               id="newUsername"
               name="newUsername"
               value="${user.username}" />

        <button type="submit">Сохранить</button>
    </form>
</body>
</html>