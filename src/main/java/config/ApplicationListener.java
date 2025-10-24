package config;

import dao.DataClass;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import service.UserService;
import service.UserServiceImpl;

public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context  = sce.getServletContext();

        DataClass dataClass = new DataClass();
        UserService userService = new UserServiceImpl(dataClass);

        context.setAttribute("userService", userService);
        //todo перенести все сервисы сюда в контекст
    }
}
