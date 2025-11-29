package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ToDoItemService;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/addToDoItem")
public class AddToDoItemServlet extends HttpServlet {
    private ToDoItemService toDoItemService;

    @Override
    public void init() throws ServletException {
        this.toDoItemService = (ToDoItemService) getServletContext().getAttribute("toDoItemService");
        if (toDoItemService == null) {
            throw new IllegalStateException("toDoItemService is null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String listIdParam = req.getParameter("listId");
        String roomIdParam = req.getParameter("roomId");
        String text = req.getParameter("text");
        String deadlineParam = req.getParameter("deadline");

        if (listIdParam == null || roomIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        long listId;
        long roomId;
        try {
            listId = Long.parseLong(listIdParam);
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        if (text == null || text.isBlank()) {
            // Можно записать ошибку в сессию/атрибут и показать в room.jsp, но минимально просто вернёмся
            resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
            return;
        }

        Timestamp deadline = null;
        if (deadlineParam != null && !deadlineParam.isBlank()) {
            // input type="datetime-local" даёт строку вида "2025-11-28T18:30"
            try {
                String ts = deadlineParam.replace("T", " ") + ":00"; // "yyyy-MM-dd HH:mm:ss"
                deadline = Timestamp.valueOf(ts);
            } catch (IllegalArgumentException ignored) {

            }
        }

        toDoItemService.create(listId, text, deadline);

        resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
    }
}