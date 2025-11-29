package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ToDoItemService;

import java.io.IOException;

@WebServlet("/completeToDoItem")
public class CompleteToDoItemServlet extends HttpServlet {
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

        String itemIdParam = req.getParameter("itemId");
        String roomIdParam = req.getParameter("roomId");

        if (itemIdParam != null && roomIdParam != null) {
            long itemId = Long.parseLong(itemIdParam);
            long roomId = Long.parseLong(roomIdParam);

            toDoItemService.toggleDone(itemId);

            resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
        } else {
            resp.sendRedirect(req.getContextPath() + "/rooms");
        }
    }
}
