<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header>
    <nav class="navigation">
        <ul>
            <button id="buttonForChangeTheme" onclick="changeTheme()">🌙</button>
            <li><a href="${pageContext.request.contextPath}/index"><button class="dropbtn">на главную</button></a></li>

            <li>
                <div class="dropdown">
                    <button onclick="accounts()" class="dropbtn">аккаунт</button>
                    <div id="accounts" class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/registration">зарегистрироваться</a>
                        <a href="${pageContext.request.contextPath}/login">войти в аккаунт</a>
                        <a href="${pageContext.request.contextPath}/profile">настроить аккаунт</a>
                    </div>
                </div>
            </li>

            <li>
                <div class="dropdown">
                    <button onclick="rooms()" class="dropbtn">к комнатам</button>
                    <div id="rooms" class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/createRoom">создать комнату</a>
                        <a href="${pageContext.request.contextPath}/connectToRoom">присоединиться к существующей комнате</a>
                    </div>
                </div>
            </li>

            <li><a href="${pageContext.request.contextPath}/rooms"><button class="dropbtn">мои комнаты</button></a></li>
        </ul>
    </nav>
</header>