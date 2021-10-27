package by.epam.courierexchange.controller.command.impl.courier;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.OrderDaoImpl;
import by.epam.courierexchange.model.entity.Order;
import by.epam.courierexchange.model.entity.OrderStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CompleteOrderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute(SessionAttribute.ORDER);
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        CommandResult commandResult;
        try {
            orderDao.updateStatus(order.getId(), OrderStatus.COMPLETED);
            commandResult = new CommandResult(PagePath.COURIER_ORDER, CommandResult.ResponseType.FORWARD);
        } catch (DaoException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
