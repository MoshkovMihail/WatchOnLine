package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ToDoListService;

import java.io.IOException;

@WebServlet("/createToDoList")
public class CreateToDoListServlet extends HttpServlet {
    private ToDoListService toDoListService;

    @Override
    public void init() throws ServletException {
        this.toDoListService = (ToDoListService) getServletContext().getAttribute("toDoListService");
        if (toDoListService == null) {
            throw new IllegalStateException("toDoListService is null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        UserEntity user = (UserEntity) session.getAttribute("user");

        String roomIdParam = req.getParameter("roomId");
        String name = req.getParameter("name");

        if (roomIdParam == null || roomIdParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        if (name == null || name.isBlank()) {
            req.setAttribute("error_message", "Название ToDo листа не может быть пустым");
            resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
            return;
        }

        toDoListService.create(roomId, user.getId(), name);

        resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
    }
}
