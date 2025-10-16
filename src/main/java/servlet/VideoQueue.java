package servlet;

import models.Video;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@WebServlet("/queue")
public class VideoQueue extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlPath = getServletContext().getRealPath("/queue.html");
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlPath)), StandardCharsets.UTF_8);

        HttpSession session = req.getSession();
        List<Video> listOfVideo = (List<Video>) session.getAttribute("listOfVideo");

        PrintWriter pw = resp.getWriter();

        pw.write(htmlContent);

        if (listOfVideo != null) {
            for (Video video : listOfVideo) {
                pw.write("\n<p>" + video.getLink() + "</p>");
            }
        }

        pw.write("\n" +
                "</body>\n" +
                "</html>");

        req.getRequestDispatcher(getServletContext().getContextPath() + "/queue.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String htmlPath = getServletContext().getRealPath("/queue.html");
        HttpSession session = req.getSession();

        String videoRef = req.getParameter("video");
        Video video = new Video(videoRef);

        List<Video> listOfVideo = (List<Video>) session.getAttribute("listOfVideo");

        if (listOfVideo == null) {
            listOfVideo = new ArrayList<>();
        }

        listOfVideo.add(video);

        session.setAttribute("listOfVideo", listOfVideo);
        System.out.println("added");

        resp.sendRedirect("/queue");
    }
}
