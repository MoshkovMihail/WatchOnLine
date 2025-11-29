package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ToDoItemService;

import java.io.IOException;

@WebServlet("/deleteToDoItem")
public class DeleteToDoItemServlet extends HttpServlet {
    private ToDoItemService toDoItemService;

    @Override
    public void init() throws ServletException {
        this.toDoItemService = (ToDoItemService) getServletContext().getAttribute("toDoItemService");
        if (toDoItemService == null) {
            throw new IllegalStateException("toDoItemService is null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemIdParam = req.getParameter("itemId");
        String roomIdParam = req.getParameter("roomId");

        if (itemIdParam == null || roomIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        long itemId;
        long roomId;
        try {
            itemId = Long.parseLong(itemIdParam);
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        toDoItemService.deleteById(itemId);

        resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
    }
}
