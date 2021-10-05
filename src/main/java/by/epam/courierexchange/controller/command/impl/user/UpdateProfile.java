package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.*;
import static by.epam.courierexchange.controller.command.RequestAttribute.*;

public class UpdateProfile implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        CommandResult commandResult;
        String name = request.getParameter(RequestParameter.NAME);
        String surname = request.getParameter(RequestParameter.SURNAME);
        String phone = request.getParameter(RequestParameter.PHONE);
        HttpSession session = request.getSession();
        Optional<User> optionalUser = Optional.ofNullable((User)session.getAttribute(SessionAttribute.USER));
        if(optionalUser.isEmpty()){
            return new CommandResult(ERROR_PAGE, FORWARD);
        }
        try{
            optionalUser = userService.updateProfile(name, surname, phone, optionalUser.get());
            if(optionalUser.isPresent()){
                User user = optionalUser.get();
                session.setAttribute(SessionAttribute.USER, user);
            }
            else{
                request.setAttribute(WRONG_VALIDATION, true);
            }
            commandResult = new CommandResult(PROFILE_PAGE, FORWARD);
        } catch (ServiceException e){
            request.setAttribute(EXCEPTION, e);
            commandResult = new CommandResult(ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
