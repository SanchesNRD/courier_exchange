package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.OrderDaoImpl;
import by.epam.courierexchange.model.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToClientOrders implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        List<Order> orders;
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(SessionAttribute.CLIENT);
        CommandResult commandResult;
        if(client != null) {
            try {
                orders = orderDao.selectHistoryByClient(client.getId(), OrderStatus.AGREED);
                session.setAttribute(SessionAttribute.ORDERS, orders);
                commandResult = new CommandResult(PagePath.USER_ORDERS_PAGE, FORWARD);
            } catch (DaoException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        }
        else{
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
        return commandResult;
    }
}
