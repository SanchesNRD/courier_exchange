package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.RequestAttribute;
import by.epam.courierexchange.controller.command.RequestParameter;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToNewPassword implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String mail = request.getParameter(RequestParameter.MAIL);
        Optional<User> optionalUser;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        CommandResult commandResult;
        try{
            optionalUser = userService.selectByMail(mail);
            if(optionalUser.isPresent()){
                request.setAttribute(RequestAttribute.USER_ID, optionalUser.get().getId());
                commandResult = new CommandResult(NEW_PASSWORD_PAGE, FORWARD);
            }else{
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
                commandResult = new CommandResult(FORGOT_PASS_PAGE, FORWARD);
            }
        } catch(ServiceException e){
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
