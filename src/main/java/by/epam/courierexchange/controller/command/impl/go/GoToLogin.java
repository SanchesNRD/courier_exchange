package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;

import static by.epam.courierexchange.controller.command.RequestAttribute.WRONG_LOGIN_OR_PASSWORD;

public class GoToLogin implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
    }
}
