package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.model.entity.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class GoToClientNewOrder implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(SessionAttribute.CLIENT);
        CommandResult commandResult;
        if(client != null) {
                commandResult =  new CommandResult(PagePath.NEW_ORDER_PAGE, FORWARD);
        }
        else{
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
        return commandResult;
    }
}
