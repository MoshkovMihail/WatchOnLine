<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/room.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>WatchOnLine</title>
    <script src="../js/script.js"></script>
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <div class="connectedUsers">
        <ul>
            <li>
                <img src="https://avatars.mds.yandex.net/i?id=040a43fa7959ca912ba74ab04414cec8806fd586-5325046-images-thumbs&n=13" alt="Авы нету("/>
            </li>
            <li>
                <img src="https://avatars.mds.yandex.net/get-yapic/48449/vRyOumsdzj36M9LLjk5QWJV6vj0-1/orig" alt="нету авы.."/>
            </li>
        </ul>
        
    </div>

<div> <a href="/queue"> <p>очередь видео </p></a> </div>

    <div class="videoPlayer">
        <img src="https://i.ytimg.com/vi/JcVY5UUaKUM/maxresdefault.jpg" alt="Здесь должен быть видеоплеер"/>
    </div>

    <div>
        <p>Код комнаты: nlsk19wqqiondnqueun113</p>
    </div>
</body>
</html>