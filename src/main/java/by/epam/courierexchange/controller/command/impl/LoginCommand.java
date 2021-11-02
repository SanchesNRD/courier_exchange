package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.CourierDaoImpl;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.Courier;
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

    @Override
    public CommandResult execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        CourierDaoImpl courierDao = CourierDaoImpl.getInstance();
        CommandResult commandResult;
        try {
            Optional<User> optionalUser = userService.authorization(login, password);
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                HttpSession session = request.getSession(true);
                session.setAttribute(SessionAttribute.USER, user);
                switch (user.getUserStatus()){
                    case NON_CONFIRMED -> {
                        commandResult = new CommandResult(PROFILE_PAGE, FORWARD);
                    }
                    case CONFIRMED -> {
                        Optional<Client> clientOptional = clientDao.selectById(user.getId());
                        clientOptional.ifPresent(client -> session.setAttribute(SessionAttribute.CLIENT, client));
                        commandResult = new CommandResult(PROFILE_PAGE, FORWARD);
                    }
                    case COURIER_CONFIRMED -> {
                        Optional<Courier> courierOptional = courierDao.selectById(user.getId());
                        courierOptional.ifPresent(courier -> session.setAttribute(SessionAttribute.COURIER, courier));
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
        } catch (ServiceException | DaoException e) {
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
