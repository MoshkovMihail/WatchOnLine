package filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter("/*")
public class LogFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String uri = req.getRequestURI();
        LocalDateTime time = LocalDateTime.now();

        HttpServletRequest request = (HttpServletRequest) req;

        String requestURI = ((HttpServletRequest) req).getRequestURI();

        if (requestURI.startsWith(request.getContextPath() + "/css") ||
                requestURI.startsWith(request.getContextPath() + "/js") ||
                requestURI.startsWith(request.getContextPath() + "/images") ||
                requestURI.endsWith(".png") ||
                requestURI.endsWith(".jpg") ||
                requestURI.endsWith(".gif")) {
            chain.doFilter(req, res);
            return;
        }

        System.out.println("log: " + "session id = " + session.getId() + " \n" + "time = " + time + "\n" + "URI = " + uri + "\n");
        chain.doFilter(req, res);
    }
}