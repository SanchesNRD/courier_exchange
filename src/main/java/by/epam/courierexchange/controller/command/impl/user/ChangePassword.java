package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class ChangePassword implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user =  (User)session.getAttribute(SessionAttribute.USER);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String newPassword = request.getParameter(RequestParameter.NEW_PASSWORD);
        CommandResult commandResult;
        try{
            Optional<User> optionalUser;
            optionalUser = userService.changePassword(user, password, newPassword);
            if(optionalUser.isPresent()){
                request.setAttribute(RequestAttribute.PASSWORD_CHANGE_SUC, true);
                session.setAttribute(SessionAttribute.USER, optionalUser.get());
            }else{
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }
            commandResult = new CommandResult(PagePath.PROFILE_PAGE, CommandResult.ResponseType.FORWARD);
        } catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
