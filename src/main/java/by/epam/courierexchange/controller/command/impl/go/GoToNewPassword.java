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
        String userId = request.getParameter(RequestParameter.ID);
        request.setAttribute(RequestAttribute.USER_ID, userId);
        return new CommandResult(NEW_PASSWORD_PAGE, FORWARD);
    }
}
