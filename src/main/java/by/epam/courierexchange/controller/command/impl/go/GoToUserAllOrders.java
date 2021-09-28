package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class GoToUserAllOrders implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null) {
            return new CommandResult(PagePath.USER_ALL_ORDERS_PAGE, FORWARD);
        }
        else{
            return new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
    }
}
