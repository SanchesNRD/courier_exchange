package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.OrderDaoImpl;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.Order;
import by.epam.courierexchange.model.entity.OrderStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToClientHistory implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute(SessionAttribute.CLIENT);
        List<Order> orders;
        CommandResult commandResult;
        if(client != null) {
            try {
                orders = orderDao.selectHistoryByClient(client.getId(), OrderStatus.COMPLETED);
                session.setAttribute(SessionAttribute.ORDERS, orders);
                commandResult = new CommandResult(PagePath.USER_HISTORY_PAGE, CommandResult.ResponseType.FORWARD);
            } catch (DaoException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        }else{
            commandResult = new CommandResult(PagePath.SIGN_UP_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
