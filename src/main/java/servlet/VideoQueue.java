package servlet;

import entity.Video;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

        for (Video video : listOfVideo) {
            pw.write("<p>" + video.getLink() + "</p>");
        }

        req.getRequestDispatcher("/html/queue.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String videoRef = req.getParameter("video");
        Video video = new Video(videoRef);

        List<Video> listOfVideo = (List<Video>) session.getAttribute("listOfVideo");
        if (listOfVideo == null) {
            listOfVideo = new ArrayList<>();
        }

        listOfVideo.add(video);

        req.setAttribute("listOfVideo", listOfVideo);
        System.out.println("added");

    }
}
