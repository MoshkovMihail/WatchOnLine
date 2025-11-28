<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>ToDo2Gether</title>

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/script.js"></script>
</head>
<body>
    <jsp:include page="/jsp/header.jsp"/>

    <main class="page">
        <div class="page-inner">
            <section class="card">
                <h1 class="page-title">ToDo2Gether</h1>
                <p class="page-subtitle">
                    Сервис, где вы можете собираться с друзьями в комнатах и вместе выполнять задачи.
                </p>

                <p>
                    1. Зарегистрируйтесь или войдите в аккаунт в разделе <strong>«аккаунт»</strong>.
                </p>
                <p>
                    2. Создайте комнату в разделе <strong>«к комнатам → создать комнату»</strong>.
                </p>
                <p>
                    3. Поделитесь кодом комнаты с другом — он сможет присоединиться через раздел
                    <strong>«к комнатам → присоединиться к существующей комнате»</strong>.
                </p>
                <p>Удачной совместной работы!</p>
            </section>
        </div>
    </main>
</body>
</html>