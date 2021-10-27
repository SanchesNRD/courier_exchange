package by.epam.courierexchange.controller.command.impl.admin;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class BanUserCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.USER_ID);
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<User> users;
        int serviceResult;
        CommandResult commandResult;
        try {
            serviceResult  = userService.banUser(id);
            if(serviceResult == 0){
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }else if(serviceResult == -1){
                request.setAttribute(RequestAttribute.USER_HAVE_ORDER, true);
            }
            users = userService.selectAll();
            session.setAttribute(SessionAttribute.USERS, users);
            commandResult = new CommandResult(PagePath.ADMIN_ALL_USERS, CommandResult.ResponseType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
