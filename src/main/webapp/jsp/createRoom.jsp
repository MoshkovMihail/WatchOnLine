<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/createRoom.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="../js/script.js"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="urlForVideo">
        <label for="name"><p>Введите ссылку на видео</p></label>
        <input type="text" id = "name"/>
        <button> Создать комнату </button>
        <a href="/room">в комнату (временная ссылка)</a>
    </div>
    
<div id="clickMe">
        
</div>

</body>
</html>