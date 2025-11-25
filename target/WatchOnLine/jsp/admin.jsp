<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <meta charset="UTF-8">
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <title>WatchOnLine_admin</title>
</head>
<body>
<nav class="navigation">
    <ul>
        <button id="buttonForChangeTheme" onclick="changeTheme()">🌙</button>
        <li><a href="/index"><button class="dropbtn">на главную</button></a></li>

        <li>
            <div class="dropdown">
                <button onclick="accounts()" class="dropbtn">аккаунт</button>
                <div id="accounts" class="dropdown-content">
                    <a href="/registration">зарегистрироваться</a>
                    <a href="/login">войти в аккаунт</a>
                    <a href="/profile">настроить аккаунт</a>
                </div>
            </div>
        </li>

        <li>
            <div class="dropdown">
                <button onclick="rooms()" class="dropbtn">к комнатам</button>
                <div id="rooms" class="dropdown-content">
                    <a href="/createRoom">создать комнату</a>
                    <a href="/connectToRoom">присоединиться к существующей комнате</a>
                </div>
            </div>
        </li>

        <li><a href="/prikols"><button class="dropbtn">всякие приколы</button></a></li>
    </ul>
</nav>

</body>
</html>