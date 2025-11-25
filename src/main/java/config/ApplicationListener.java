package config;

import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import service.UserService;
import service.UserServiceImpl;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context  = sce.getServletContext();

        UserDAO userDAO = new UserDAO();

        userDAO.createUserTable();

        UserService userService = new UserServiceImpl(userDAO);

        context.setAttribute("userService", userService);
        //todo перенести все сервисы сюда в контекст
    }
}
