<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
     <link href="${pageContext.request.contextPath}/css/profile.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>


<body>
    <jsp:include page="navbar.jsp"/>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button>Выйти из аккаунта</button>
    </form>

    <div id="account">
        <p>Ваш аккаунт:</p>
    </div>

    <div class="avatar">
        <img src="https://avatars.mds.yandex.net/i?id=040a43fa7959ca912ba74ab04414cec8806fd586-5325046-images-thumbs&n=13" alt="Авы нету("/>
    </div>


    <div class="username">
        <p>username:</p>
        <c:out value="${username}"/>
    </div>

    <div class="email">
        <p>email:</p>
        <p><c:out value="${email}"/></p>
    </div>

    <div class="password"></div>
</body>
</html>