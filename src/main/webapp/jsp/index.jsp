<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/index.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="../js/script.js"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>


    <strong>
        <div id="Hello">
             <p>Добро пожаловать на WatchOnLine</p>
        </div>



      <div id="gaid">
        <p>
            Чтобы создать комнату вам необходимо зарегестрироваться
        </p>

        <p>
            Далее пройдите в раздел "создать комнату" и введите ссылку на видео которое хотите посмотреть вместе
        </p>

        <p>
            В комнате будет код, пришлите его своему другу, он сможет подключиться введя ссылку в разделе "присоединиться к существующей комнате"
        </p>

        <p>Удачного просмотра!</p>

      </div>

    </strong>

</body>
</html>