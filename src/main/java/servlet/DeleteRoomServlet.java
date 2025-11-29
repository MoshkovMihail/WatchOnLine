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

@WebServlet("/deleteRoom")
public class DeleteRoomServlet extends HttpServlet {
    private RoomService roomService;

    @Override
    public void init() throws ServletException {
        this.roomService = (RoomService) getServletContext().getAttribute("roomService");
        if (roomService == null) {
            throw new IllegalStateException("roomService is null");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("user");



        String roomIdParam = req.getParameter("roomId");
        String ctx = req.getContextPath();


        if (roomIdParam == null || roomIdParam.isBlank()) {
            resp.sendRedirect(ctx + "/rooms");
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(ctx + "/rooms");
            return;
        }


        roomService.deleteRoom(roomId, user.getId());


        resp.sendRedirect(ctx + "/rooms");
    }
}

