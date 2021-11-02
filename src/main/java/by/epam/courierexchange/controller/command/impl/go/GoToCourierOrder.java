package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.Order;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.CourierServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class GoToCourierOrder implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        CourierServiceImpl courierService = CourierServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Courier courier = (Courier) session.getAttribute(SessionAttribute.COURIER);
        Optional<Order> orderOptional;
        CommandResult commandResult;
        if(courier != null) {
            try {
                orderOptional = courierService.selectActiveOrderByCourier(courier.getId());
                if (orderOptional.isPresent()) {
                    session.setAttribute(SessionAttribute.ORDER, orderOptional.get());
                } else {
                    request.setAttribute(RequestAttribute.NO_ORDER, true);
                }
                commandResult = new CommandResult(PagePath.COURIER_ORDER, CommandResult.ResponseType.FORWARD);
            } catch (ServiceException e) {
                commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
            }
        }else{
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
        }

        return commandResult;
    }
}
