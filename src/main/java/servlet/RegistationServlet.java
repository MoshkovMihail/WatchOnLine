package servlet;

import dao.DataClass;
import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserServiceImpl;
import util.HashUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Этот сервлет должен отрабатывать,
 * когда пользователь захочет рзарегистрироваться в приложении
 */
@WebServlet("/registration")
public class RegistationServlet extends HttpServlet {
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new DataClass());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        req.getRequestDispatcher("/html/registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
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
            String htmlContent = getHtmlWithError(errorMessage, login, email);
            printWriter.write(htmlContent);
            return;
        }

        password = HashUtil.hashPassword(password);

        userService.saveUserInDb(login, email, password);
        resp.sendRedirect("/login");
        printWriter.write("Спасибо");

        //safeInFile(login, email, password);

        printWriter.close();


    }

    private String getHtmlWithError(String errorMessage, String login, String email) throws IOException {
        String htmlPath = getServletContext().getRealPath("/html/registration.html");
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlPath)), StandardCharsets.UTF_8);


        String errorDiv = "<div class='error-message' style='color: red; text-align: center; padding: 20px; margin: 20px; border: 2px solid red; background: #ffeeee;'>"
                + "<strong>Ошибка:</strong> " + errorMessage
                + "</div>";


        htmlContent = htmlContent.replace("placeholder=\"Логин\"", "placeholder=\"Логин\" value=\"" + (login != null ? login : "") + "\"");
        htmlContent = htmlContent.replace("placeholder=\"Email\"", "placeholder=\"Email\" value=\"" + (email != null ? email : "") + "\"");


        htmlContent = htmlContent.replace("<form action=\"registration\"", errorDiv + "<form action=\"registration\"");

        return htmlContent;
    }

    private void safeInFile(String login, String email, String password) throws IOException {

        try{
            FileWriter out = new FileWriter(login + ".txt");
            PrintWriter pw = new PrintWriter(out);

            pw.println(login);
            pw.println(email);
            pw.println(password);

            pw.close();
        } catch (IOException e){
            System.out.println("Ошибка при записи файла");
        }
    }
}
