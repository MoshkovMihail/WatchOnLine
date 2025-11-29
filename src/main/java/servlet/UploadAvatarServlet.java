package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet("/uploadAvatar")
@MultipartConfig
public class UploadAvatarServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
        if (userService == null) {
            throw new IllegalStateException("userService s null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Part filePart = req.getPart("avatar");
        if (filePart == null || filePart.getSize() == 0) {
            // ничего не выбрали – просто вернёмся в профиль
            resp.sendRedirect(req.getContextPath() + "/profile");
            return;
        }

        // имя файла и расширение
        String submitted = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String ext = "";
        int dot = submitted.lastIndexOf('.');
        if (dot != -1) {
            ext = submitted.substring(dot).toLowerCase();
        }

        // Разрешённые форматы
        if (!ext.matches("\\.(png|jpg|jpeg|gif)")) {
            req.setAttribute("error_message", "Разрешены только png, jpg, jpeg, gif");
            req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
            return;
        }

        // Папка внутри webapp: /avatars
        String avatarsDirRealPath = getServletContext().getRealPath("/avatars");
        Files.createDirectories(Paths.get(avatarsDirRealPath));

        // Имя файла: user_ИД.расширение
        String newFileName = "user_" + user.getId() + ext;
        Path target = Paths.get(avatarsDirRealPath, newFileName);

        try (InputStream in = filePart.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }

        // Путь, который будем хранить в БД и использовать в <img src="">
        String dbPath = "/avatars/" + newFileName;

        // Обновляем БД
        userService.updateAvatarPath(user.getId(), dbPath);

        // Обновляем объект в сессии
        user.setAvatarPath(dbPath);
        session.setAttribute("user", user);

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
