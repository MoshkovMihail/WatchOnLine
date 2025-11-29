<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header class="main-header">
    <nav class="navigation">
        <ul class="nav-list">
            <!-- Кнопка смены темы -->
            <li class="nav-item nav-item-theme">
                <button id="buttonForChangeTheme"
                        type="button"
                        onclick="changeTheme()"
                        aria-label="Сменить тему">
                    🌙
                </button>
            </li>

            <!-- На главную -->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/index" class="dropbtn nav-link">
                    на главную
                </a>
            </li>

            <!-- Аккаунт: выпадающее меню -->
            <li class="nav-item">
                <div class="dropdown">
                    <button type="button"
                            onclick="accounts()"
                            class="dropbtn">
                        аккаунт
                    </button>
                    <div id="accounts" class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/registration">зарегистрироваться</a>
                        <a href="${pageContext.request.contextPath}/login">войти в аккаунт</a>
                        <a href="${pageContext.request.contextPath}/profile">настроить аккаунт</a>
                    </div>
                </div>
            </li>

            <!-- Комнаты: выпадающее меню -->
            <li class="nav-item">
                <div class="dropdown">
                    <button type="button"
                            onclick="rooms()"
                            class="dropbtn">
                        к комнатам
                    </button>
                    <div id="rooms" class="dropdown-content">
                        <a href="${pageContext.request.contextPath}/createRoom">создать комнату</a>
                        <a href="${pageContext.request.contextPath}/connectToRoom">присоединиться к существующей комнате</a>
                    </div>
                </div>
            </li>

            <!-- Мои комнаты -->
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/rooms" class="dropbtn nav-link">
                    мои комнаты
                </a>
            </li>
        </ul>
    </nav>
</header>
