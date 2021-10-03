package by.epam.courierexchange.controller;


import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = "/image_display")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageDisplayController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String UPLOAD_DIRECTORY = "C:/Users/alex2/epam/img/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = (User)req.getSession().getAttribute(SessionAttribute.USER);
        long id = currentUser.getId();
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String path = null;
        try {
            path = userService.findImgPath(id);
        } catch (ServiceException e) {
            logger.error("ServiceException in method doGet while found path to picture ", e);
            resp.sendRedirect(PagePath.ERROR_PAGE);
        }
        byte[] imageBytes = Files.readAllBytes(Paths.get(path));
        resp.setContentType("image/jpeg");
        resp.setContentLength(imageBytes.length);
        resp.getOutputStream().write(imageBytes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        String fileName = null;
        if (!uploadDir.exists()) {uploadDir.mkdir();}
        for (Part part : req.getParts()) {
            fileName = part.getSubmittedFileName();
            part.write(uploadPath + File.separator + fileName);
        }

        UserServiceImpl userService = UserServiceImpl.getInstance();
        User currentUser = (User)req.getSession().getAttribute(SessionAttribute.USER);
        long id = currentUser.getId();
        try{
            userService.uploadImgPath(id, "" + fileName);
        } catch (ServiceException e){
            logger.error("ServiceException to the upload file: ", e);
            req.getRequestDispatcher(PagePath.ERROR_PAGE).forward(req, resp);
        }

        req.getRequestDispatcher(PagePath.PROFILE_PAGE).forward(req, resp);
    }
}
