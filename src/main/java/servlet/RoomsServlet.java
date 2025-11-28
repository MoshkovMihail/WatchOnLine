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
import java.util.List;

@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {
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
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");

        List<RoomEntity> rooms = roomService.getRoomsForUser(user.getId());
        req.setAttribute("rooms", rooms);

        req.getRequestDispatcher("/jsp/rooms.jsp").forward(req, resp);
    }

}
