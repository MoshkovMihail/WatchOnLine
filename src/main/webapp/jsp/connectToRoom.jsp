<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/connectToRoom.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="../js/script.js"></script>
</head>
<body>
  <jsp:include page="navbar.jsp"/>
  <div class="join">
    <label for="enter"><p>Введите код комнаты</p></label>
    <input type="text" id = "enter" />
    <button>Присоединиться к комнате</button>
  </div>



</body>
</html>