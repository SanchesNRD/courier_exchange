package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class ConfirmProfileCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        String userId = request.getParameter(RequestParameter.ID);
        CommandResult commandResult;
        try{
            if(userService.changeRole(userId, UserStatus.CONFIRMED)!=0){
                clientService.createClient(userId);
            }
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
        }catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
