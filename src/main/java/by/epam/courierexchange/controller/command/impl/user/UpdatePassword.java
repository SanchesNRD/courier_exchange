package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.RequestAttribute;
import by.epam.courierexchange.controller.command.RequestParameter;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class UpdatePassword implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String userId =  request.getParameter(RequestParameter.USER_ID);
        String password = request.getParameter(RequestParameter.PASSWORD);
        CommandResult commandResult;
        try{
            if(userService.updatePassword(userId, password)){
                commandResult = new CommandResult(LOGIN_PAGE, FORWARD);
            }else{
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
                commandResult = new CommandResult(NEW_PASSWORD_PAGE, FORWARD);
            }
        } catch (ServiceException e){
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
