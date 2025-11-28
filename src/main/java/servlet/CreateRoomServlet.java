package servlet;

import entity.RoomEntity;
import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RoomService;

import java.io.IOException;

@WebServlet("/createRoom")
public class CreateRoomServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init() throws ServletException {
        this.roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            throw new IllegalStateException("roomService is null");
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/createRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (name == null || name.trim().isEmpty()) {
            req.setAttribute("error_message", "Название комнаты не может быть пустым");
            req.getRequestDispatcher("/jsp/createRoom.jsp").forward(req, resp);
            return;
        }


        RoomEntity room = roomService.createRoom(name, user.getId());

        resp.sendRedirect(req.getContextPath() + "/room?id=" + room.getId());
    }
}
