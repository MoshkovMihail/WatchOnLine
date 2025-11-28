package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;

import javax.naming.Context;
import java.io.IOException;

@WebServlet("/updateUsername")
public class UpdateUsernameServlet extends HttpServlet {
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
        HttpSession session = req.getSession();
        String ctx = req.getContextPath();
        UserEntity user = (UserEntity) session.getAttribute("user");

        String newUsername = req.getParameter("newUsername");


        if (!userService.updateUsername(newUsername, user.getId())) {
            req.setAttribute("error_message", "Имя пользователя уже занято");
        } else {
            user.setUsername(newUsername);
            session.setAttribute("user", user);
            req.setAttribute("success_message", "Имя пользователя успешно изменено");
        }

        resp.sendRedirect(ctx + "/profile");
    }
}
