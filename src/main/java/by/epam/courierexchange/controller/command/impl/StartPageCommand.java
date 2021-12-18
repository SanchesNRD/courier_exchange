package by.epam.courierexchange.controller.command.impl;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;


public class StartPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        return new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
    }
}
