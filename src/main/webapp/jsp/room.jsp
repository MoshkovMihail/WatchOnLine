<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/room.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>ToDo2Gether – Комната</title>
    <script src="${pageContext.request.contextPath}/js/script.js"></script>
    <script src="${pageContext.request.contextPath}/js/room.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="room-page">

        <!-- ===== Заголовок комнаты + удалить комнату ==== -->
        <header class="room-header">
            <div class="room-header__info">
                <h1 class="room-title">
                    Комната: <c:out value="${room.name}"/>
                </h1>
                <p class="room-meta">
                    ID комнаты: <span class="room-id"><c:out value="${room.id}"/></span>
                </p>
            </div>

            <c:if test="${user.id == room.ownerId}">
                <form action="${pageContext.request.contextPath}/deleteRoom"
                      method="post"
                      onsubmit="return confirm('Точно удалить комнату?');">
                    <input type="hidden" name="roomId" value="${room.id}"/>
                    <button type="submit" class="btn btn-danger">
                        Удалить комнату
                    </button>
                </form>
            </c:if>
        </header>

        <!-- ===== Участники комнаты ======================= -->
        <section class="room-section">
            <div class="room-section__header">
                <h2>Участники комнаты</h2>
            </div>

            <c:if test="${empty members}">
                <p class="room-muted">Пока в комнате только вы.</p>
            </c:if>

            <c:if test="${not empty members}">
                <ul class="members-list">
                    <c:forEach var="m" items="${members}">
                        <li class="member">
                            <div class="member-avatar">
                                <img src="${pageContext.request.contextPath}${m.avatarPath}"
                                     alt="Аватар"/>
                            </div>
                            <div class="member-info">
                                <span class="member-name">
                                    <c:out value="${m.username}"/>
                                </span>
                                <c:if test="${user != null && m.id == user.id}">
                                    <span class="member-tag">это вы</span>
                                </c:if>
                                <c:if test="${room.ownerId == m.id}">
                                    <span class="member-tag member-tag--owner">создатель</span>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </section>

        <!-- ===== Списки задач ============================ -->
        <section class="room-section room-section--lists">
            <div class="room-section__header">
                <div>
                    <h2>Списки задач в этой комнате</h2>
                    <c:if test="${empty todoLists}">
                        <p class="room-muted">
                            Пока нет ни одного списка задач.
                        </p>
                    </c:if>
                </div>

                <!-- Форма создания нового списка -->
                <div class="create-list">
                    <form action="${pageContext.request.contextPath}/createToDoList" method="post"
                          class="create-list__form">
                        <input type="hidden" name="roomId" value="${room.id}"/>

                        <input type="text"
                               name="name"
                               class="input-text"
                               placeholder="Название нового списка"/>

                        <button type="submit" class="btn">
                            Создать
                        </button>
                    </form>

                    <c:if test="${not empty error_message}">
                        <div class="error create-list__error">${error_message}</div>
                    </c:if>
                </div>
            </div>

            <!-- Сетки списков -->
            <c:if test="${not empty todoLists}">
                <div class="todo-lists-grid">
                    <c:forEach var="list" items="${todoLists}">
                        <div class="todo-list-card">

                            <!-- Заголовок списка + создатель + удалить список -->
                            <div class="todo-list-card__header">
                                <div>
                                    <h3 class="todo-list-title">
                                        <c:out value="${list.name}"/>
                                    </h3>

                                    <c:set var="creatorName" value="неизвестно"/>
                                    <c:forEach var="m" items="${members}">
                                        <c:if test="${m.id == list.createdBy}">
                                            <c:set var="creatorName" value="${m.username}"/>
                                        </c:if>
                                    </c:forEach>

                                    <p class="todo-list-meta">
                                        Создал(а):
                                        <c:choose>
                                            <c:when test="${user != null && list.createdBy == user.id}">
                                                вы
                                            </c:when>
                                            <c:otherwise>
                                                <strong><c:out value="${creatorName}"/></strong>
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>

                                <c:if test="${user.id == list.createdBy}">
                                    <form action="${pageContext.request.contextPath}/deleteToDoList"
                                          method="post">
                                        <input type="hidden" name="listId" value="${list.id}"/>
                                        <input type="hidden" name="userId" value="${user.id}"/>
                                        <input type="hidden" name="roomId" value="${room.id}"/>
                                        <button type="submit" class="icon-button" title="Удалить список">
                                            ✕
                                        </button>
                                    </form>
                                </c:if>
                            </div>

                            <!-- Задачи этого списка -->
                            <c:set var="items" value="${todoItemsByList[list.id]}"/>

                            <c:if test="${empty items}">
                                <p class="room-muted">В этом списке пока нет задач.</p>
                            </c:if>

                            <c:if test="${not empty items}">
                                <ul class="todo-items">
                                    <c:forEach var="item" items="${items}">
                                        <li class="todo-item">
                                            <div class="todo-item__main">
                                                <c:choose>
                                                    <c:when test="${item.done}">
                                                        <span class="todo-text todo-text--done">
                                                            <c:out value="${item.text}"/>
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="todo-text">
                                                            <c:out value="${item.text}"/>
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>

                                                <!-- Дедлайн и таймер -->
                                                <c:if test="${item.deadline != null}">
                                                    <div class="todo-deadline">
                                                        <span class="todo-deadline__date">
                                                            до
                                                            <fmt:formatDate value="${item.deadline}"
                                                                            pattern="dd.MM.yyyy HH:mm"/>
                                                        </span>

                                                        <c:if test="${!item.done}">
                                                            <fmt:formatDate value="${item.deadline}"
                                                                            pattern="yyyy-MM-dd'T'HH:mm:ss"
                                                                            var="deadlineIso"/>
                                                            <span class="deadline-timer"
                                                                  data-deadline="${deadlineIso}">
                                                            </span>
                                                        </c:if>
                                                    </div>
                                                </c:if>
                                            </div>

                                            <!-- Кнопки действий по задаче -->
                                            <c:if test="${user.id == list.createdBy}">
                                                <div class="todo-item__actions">

                                                    <!-- Галочка "выполнено" – если задача ещё не done -->
                                                    <c:if test="${!item.done}">
                                                        <form action="${pageContext.request.contextPath}/completeToDoItem"
                                                              method="post" class="inline-form">
                                                            <input type="hidden" name="itemId" value="${item.id}"/>
                                                            <input type="hidden" name="roomId" value="${room.id}"/>
                                                            <button type="submit"
                                                                    class="icon-button icon-button--ok"
                                                                    title="Отметить выполненным">
                                                                ✔
                                                            </button>
                                                        </form>
                                                    </c:if>

                                                    <!-- Удалить задачу -->
                                                    <form action="${pageContext.request.contextPath}/deleteToDoItem"
                                                          method="post" class="inline-form">
                                                        <input type="hidden" name="itemId" value="${item.id}"/>
                                                        <input type="hidden" name="roomId" value="${room.id}"/>
                                                        <button type="submit"
                                                                class="icon-button icon-button--danger"
                                                                title="Удалить задачу">
                                                            ✕
                                                        </button>
                                                    </form>
                                                </div>
                                            </c:if>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>

                            <!-- Форма добавления задачи (только создатель списка) -->
                            <c:if test="${user.id == list.createdBy}">
                                <form action="${pageContext.request.contextPath}/addToDoItem"
                                      method="post"
                                      class="add-item-form">
                                    <input type="hidden" name="listId" value="${list.id}"/>
                                    <input type="hidden" name="roomId" value="${room.id}"/>

                                    <input type="text"
                                           name="text"
                                           class="input-text"
                                           placeholder="Новая задача"/>

                                    <input type="datetime-local"
                                           name="deadline"
                                           class="input-datetime"/>

                                    <button type="submit" class="btn btn-secondary">
                                        Добавить
                                    </button>
                                </form>
                            </c:if>

                        </div> <!-- /.todo-list-card -->
                    </c:forEach>
                </div> <!-- /.todo-lists-grid -->
            </c:if>
        </section>

    </main>
</body>
</html>
