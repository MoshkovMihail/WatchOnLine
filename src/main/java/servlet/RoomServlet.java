package servlet;

import entity.RoomEntity;
import entity.ToDoItemEntity;
import entity.ToDoListEntity;
import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RoomService;
import service.ToDoItemService;
import service.ToDoListService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
    private RoomService roomService;
    private ToDoListService toDoListService;
    private ToDoItemService toDoItemService;

    @Override
    public void init() throws ServletException {
        this.roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            throw new IllegalStateException("roomService is null");
        }

        this.toDoListService = (ToDoListService) getServletContext().getAttribute("toDoListService");
        if (toDoListService == null) {
            throw new IllegalStateException("toDoListService is null");
        }

        this.toDoItemService = (ToDoItemService) getServletContext().getAttribute("toDoItemService");
        if (toDoItemService == null) {
            throw new IllegalStateException("toDoItemService is null");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }

        RoomEntity room = roomService.getRoomById(roomId);
        if (room == null) {
            resp.sendRedirect(req.getContextPath() + "/rooms");
            return;
        }



        var todoLists = toDoListService.findByRoomId(roomId);


        var members = roomService.getRoomMembers(roomId);

        Map<Long, List<ToDoItemEntity>> itemsByList = new HashMap<>();
        for (ToDoListEntity list : todoLists) {
            itemsByList.put(list.getId(), toDoItemService.findByListId(list.getId()));
        }


        req.setAttribute("room", room);
        req.setAttribute("todoLists", todoLists);
        req.setAttribute("members", members);
        req.setAttribute("todoItemsByList", itemsByList);

        req.getRequestDispatcher("/jsp/room.jsp").forward(req, resp);
    }
}