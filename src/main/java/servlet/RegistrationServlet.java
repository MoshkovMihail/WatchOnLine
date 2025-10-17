package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter printWriter = resp.getWriter();
//        printWriter.write("Hello");
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

        if (login == null) {
            errorMessage = "Логин не может быть пустым";
        } else if (email == null) {
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

        printWriter.write("Спасибо");

        safeInFile(login, email, password);

        printWriter.close();
    }

    private String getHtmlWithError(String errorMessage, String login, String email) throws IOException {
        String htmlPath = getServletContext().getRealPath("/registration.html");
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
