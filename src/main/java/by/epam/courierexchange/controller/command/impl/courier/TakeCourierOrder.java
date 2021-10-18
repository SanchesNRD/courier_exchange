package by.epam.courierexchange.controller.command.impl.courier;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.CourierDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.CourierServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class TakeCourierOrder implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String clientOrderId = request.getParameter(RequestParameter.CLIENT_ORDER);
        HttpSession session = request.getSession();
        Courier courier = (Courier)session.getAttribute(SessionAttribute.COURIER);
        CourierServiceImpl courierService = CourierServiceImpl.getInstance();
        CommandResult commandResult;
        int result;
        try{
            result = courierService.createOrder(clientOrderId, courier.getId());
            if(result != 0){
                commandResult = new CommandResult(PagePath.COURIER_ORDER, CommandResult.ResponseType.FORWARD);
            }else{
                request.setAttribute(RequestAttribute.COURIER_HAVE_ORDER, true);
                commandResult = new CommandResult(PagePath.COURIER_ORDER, CommandResult.ResponseType.FORWARD);
            }
        }catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }

        return commandResult;
    }
}
