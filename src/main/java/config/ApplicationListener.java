package config;

import dao.RoomDAO;
import dao.ToDoItemDAO;
import dao.ToDoListDAO;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.*;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context  = sce.getServletContext();

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserServiceImpl(userDAO);
        context.setAttribute("userService", userService);

        RoomDAO roomDAO = new RoomDAO();
        RoomService roomService = new RoomServiceImpl(roomDAO);
        context.setAttribute("roomService", roomService);

        ToDoListDAO toDoListDAO= new ToDoListDAO();
        ToDoListService toDoListService = new ToDoListServiceImpl(toDoListDAO);
        context.setAttribute("toDoListService", toDoListService);

        ToDoItemDAO toDoItemDAO = new ToDoItemDAO();
        ToDoItemService toDoItemService = new ToDoItemServiceImpl(toDoItemDAO);
        context.setAttribute("toDoItemService", toDoItemService);
    }
}
