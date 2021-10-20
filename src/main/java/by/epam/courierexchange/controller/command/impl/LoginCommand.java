package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.REDIRECT;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.*;
import static by.epam.courierexchange.controller.command.RequestParameter.LOGIN;
import static by.epam.courierexchange.controller.command.RequestParameter.PASSWORD;

public class LoginCommand implements Command {
    private final String role_client = "client";
    private final String role_admin = "admin";

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        CommandResult commandResult;
        try {
            Optional<User> optionalUser = userService.authorization(login, password);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                HttpSession session = request.getSession(true);
                session.setAttribute(SessionAttribute.USER, user);
                switch (user.getUserStatus()){
                    case CONFIRMED, COURIER_CONFIRMED, NON_CONFIRMED -> {
                        commandResult = new CommandResult(PROFILE_PAGE, FORWARD);
                    }
                    case ADMIN -> {
                        commandResult = new CommandResult(ADMIN_PAGE, FORWARD);
                    }
                    case BANED -> {
                        request.setAttribute(BANNED_USER, true);
                        commandResult = new CommandResult(LOGIN_PAGE, FORWARD);
                    }
                    default -> {
                        request.setAttribute(WRONG_LOGIN_OR_PASSWORD,true);
                        commandResult = new CommandResult(LOGIN_PAGE, FORWARD);
                    }
                }
            }
            else{
                request.setAttribute(WRONG_LOGIN_OR_PASSWORD,true);
                commandResult = new CommandResult(LOGIN_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
