package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ToDoListService;

import java.io.IOException;

@WebServlet("/deleteToDoList")
public class DeleteToDoListServlet extends HttpServlet {
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
        String listIdParam = req.getParameter("listId");
        String userIdParam = req.getParameter("userId");
        String roomIdParam = req.getParameter("roomId");

        if (listIdParam == null || userIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        long roomId;
        long listId;
        long userId;
        try {
            roomId = Long.parseLong(roomIdParam);
            listId = Long.parseLong(listIdParam);
            userId = Long.parseLong(userIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        toDoListService.deleteById(listId, userId);

        resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
    }
}
