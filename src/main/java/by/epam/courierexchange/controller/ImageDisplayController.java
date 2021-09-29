package by.epam.courierexchange.controller;


import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.model.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/image_display")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageDisplayController extends HttpServlet {
    static final String UPLOAD_DIRECTORY = "/demo/";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = null;
        for (Part part : req.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }
        req.setAttribute("upload_result", fileName + "  success" );
        req.getRequestDispatcher("jsp/user/orders.jsp").forward(req, resp);
    }
}
