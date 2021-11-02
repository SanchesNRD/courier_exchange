package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToCourierAllOrders implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        List<ClientProduct> clientProducts;
        HttpSession session = request.getSession();
        CommandResult commandResult;
        Courier courier = (Courier)session.getAttribute(SessionAttribute.COURIER);
        if(courier != null) {
            try {
                clientProducts = clientService.selectClientProductForCourier(courier.getId(), courier.getTransport());
                session.setAttribute(SessionAttribute.CLIENT_PRODUCT, clientProducts);
                commandResult = new CommandResult(PagePath.COURIER_ALL_ORDERS_PAGE, FORWARD);
            } catch (ServiceException e) {
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
