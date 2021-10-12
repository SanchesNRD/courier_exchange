package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class ConfirmProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        CommandResult commandResult;
        try{
            userService.confirmProfile(user);
            Optional<Client> optionalClient = clientService.createClient(user);
            // TODO: 10.10.2021 add if-else
            session.setAttribute(SessionAttribute.USER, optionalClient.get());
            commandResult = new CommandResult(PagePath.PROFILE_PAGE, CommandResult.ResponseType.FORWARD);
        }catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
