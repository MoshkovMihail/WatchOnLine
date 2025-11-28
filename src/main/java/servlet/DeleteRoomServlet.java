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

        // нет параметра или он пустой → назад на список комнат
        if (roomIdParam == null || roomIdParam.isBlank()) {
            resp.sendRedirect(ctx + "/rooms");
            return;
        }

        long roomId;
        try {
            roomId = Long.parseLong(roomIdParam);
        } catch (NumberFormatException e) {
            // кто-то полез руками править форму / URL
            resp.sendRedirect(ctx + "/rooms");
            return;
        }

        try {
            roomService.deleteRoom(roomId, user.getId());
        } catch (IllegalStateException ex) {
            // здесь можно потом добавить сообщение об ошибке в сессию
            // session.setAttribute("rooms_error", ex.getMessage());
        }

        resp.sendRedirect(ctx + "/rooms");
    }
}

