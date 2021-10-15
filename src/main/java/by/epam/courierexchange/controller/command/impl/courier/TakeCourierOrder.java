package by.epam.courierexchange.controller.command.impl.courier;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

public class TakeCourierOrder implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String clientOrderId = request.getParameter(RequestParameter.CLIENT_ORDER);
        return new CommandResult(PagePath.COURIER_ORDER, CommandResult.ResponseType.FORWARD);
    }
}
