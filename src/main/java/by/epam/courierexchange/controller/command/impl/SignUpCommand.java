package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.*;
import static by.epam.courierexchange.controller.command.RequestParameter.*;

public class SignUpCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String mail = request.getParameter(MAIL);
        String phone = request.getParameter(PHONE);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        CommandResult commandResult;
        try{
            if(userService.registration(login, password, name, surname, mail, phone)!=0){
                commandResult = new CommandResult(LOGIN_PAGE, FORWARD);
            } else{
                request.setAttribute(WRONG_VALIDATION,true);
                commandResult = new CommandResult(SIGN_UP_PAGE, FORWARD);
            }
        } catch (ServiceException e){
            request.setAttribute(WRONG_SIGN_UP, true);
            commandResult = new CommandResult(SIGN_UP_PAGE, FORWARD);
        }
        return commandResult;
    }
}
