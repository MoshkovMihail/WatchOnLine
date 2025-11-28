package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import java.io.IOException;

@WebServlet("/registration")
public class RegistationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = (UserService) getServletContext().getAttribute("userService");
        if (userService == null) {
            throw new IllegalStateException("userService s null");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String ctx = req.getContextPath();
        System.out.println(ctx);

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        String errorMessage = null;

        if (login == null || login.trim().isBlank() ) {
            errorMessage = "Username не может быть пустым";
        }else if (userService.isUserExist(login)){
            errorMessage = "имя пользователя уже занято";
        } else if (email == null || email.trim().isBlank() ) {
            errorMessage = "Email не может быть пустым";
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            errorMessage = "Введите корректный email";
        } else if (password == null || password.trim().isEmpty()) {
            errorMessage = "Пароль не может быть пустым";
        }

        if (errorMessage != null) {
            req.setAttribute("error_message", errorMessage);

            req.setAttribute("login", login);
            req.setAttribute("password", password);

            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
            return;
        }

        userService.saveUserInDb(login, email, password);

        resp.sendRedirect(ctx + "/login");
    }
}
