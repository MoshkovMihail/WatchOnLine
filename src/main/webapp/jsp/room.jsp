<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

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
    <!-- Кнопка удаления комнаты, как было -->
    <form action="${pageContext.request.contextPath}/deleteRoom" method="post"
          onsubmit="return confirm('Точно удалить комнату?');">
        <input type="hidden" name="roomId" value="${room.id}"/>
        <button type="submit">Удалить комнату</button>
    </form>

    <h2>Комната: <c:out value="${room.name}"/></h2>

    <!-- Участники комнаты -->
    <h3>Участники комнаты</h3>

    <c:if test="${empty members}">
        <p>Пока в комнате только вы.</p>
    </c:if>

    <c:if test="${not empty members}">
        <ul>
            <c:forEach var="m" items="${members}">
                <li>
                    <c:out value="${m.username}"/>
                    <c:if test="${user != null && m.id == user.id}">
                        (вы)
                    </c:if>
                    <img src="${pageContext.request.contextPath}${m.avatarPath}" alt="Авы нету("/>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <hr/>

    <!-- Списки задач комнаты -->
    <h3>Списки задач в этой комнате</h3>

    <c:if test="${empty todoLists}">
        <p>Пока нет ни одного списка задач.</p>
    </c:if>

    <c:if test="${not empty todoLists}">
        <ul>
            <c:forEach var="list" items="${todoLists}">
                <li>
                    <a href="${pageContext.request.contextPath}/todoList?id=${list.id}">
                        <c:out value="${list.name}"/>
                    </a>
                    –
                    <c:choose>
                        <c:when test="${user != null && list.createdBy == user.id}">
                            создан вами
                        </c:when>
                        <c:otherwise>
                            создан пользователем
                            <strong>
                                <c:forEach var="m" items="${members}">
                                    <c:if test="${m.id == list.createdBy}">
                                        <c:out value="${m.username}"/>
                                    </c:if>
                                </c:forEach>
                            </strong>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <h4>Создать новый список задач</h4>
    <form action="${pageContext.request.contextPath}/createToDoList" method="post">
        <c:if test="${not empty error_message}">
                <div class="error">${error_message}</div>
        </c:if>
        <input type="hidden" name="roomId" value="${room.id}"/>
        <input type="text" name="name" placeholder="Название списка"/>
        <button type="submit">Создать</button>
    </form>



</body>
</html>