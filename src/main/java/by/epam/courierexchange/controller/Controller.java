package by.epam.courierexchange.controller;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandProvider;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.model.connection.ConnectionPool;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.epam.courierexchange.controller.command.RequestParameter.COMMAND;

@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        CommandResult commandResult = command.execute(request);
        switch (commandResult.getResponseType()) {
            case FORWARD -> {
                RequestDispatcher dispatcher = request.getRequestDispatcher(commandResult.getPagePath());
                dispatcher.forward(request, response);
            }
            case REDIRECT -> response.sendRedirect(commandResult.getPagePath());
            default -> response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}