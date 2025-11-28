package servlet;

import entity.UserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.RoomService;

import java.io.IOException;

@WebServlet("/connectToRoom")
public class ConnectToRoomServlet extends HttpServlet {
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
        req.getRequestDispatcher("/jsp/connectToRoom.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");

        String roomIdParam = req.getParameter("room_id");

        if (roomIdParam == null || roomIdParam.isBlank()) {
            req.setAttribute("error_message", "Введите id комнаты");
            req.getRequestDispatcher("/jsp/connectToRoom.jsp").forward(req, resp);
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            req.setAttribute("error_message", "Некорректный формат id комнаты");
            req.getRequestDispatcher("/jsp/connectToRoom.jsp").forward(req, resp);
            return;
        }

        if (roomService.joinRoom(roomId, user.getId())) {
            resp.sendRedirect(req.getContextPath() + "/room?id=" + roomId);
            return;
        }


        req.setAttribute("error_message", "Комнаты с таким id не существует");
        req.getRequestDispatcher("/jsp/connectToRoom.jsp").forward(req, resp);
    }
}
