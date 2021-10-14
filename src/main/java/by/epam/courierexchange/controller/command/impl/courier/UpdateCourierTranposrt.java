package by.epam.courierexchange.controller.command.impl.courier;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Address;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.Transport;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.CourierServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class UpdateCourierTranposrt implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String name = request.getParameter(RequestParameter.NAME);
        String speed = request.getParameter(RequestParameter.SPEED);
        String weight = request.getParameter(RequestParameter.WEIGHT);
        String type = request.getParameter(RequestParameter.TRANSPORT_TYPE);

        HttpSession session = request.getSession();
        Courier courier = (Courier) session.getAttribute(SessionAttribute.COURIER);
        CourierServiceImpl courierService = CourierServiceImpl.getInstance();
        Optional<Transport> transport;
        CommandResult commandResult;
        try{
            transport = courierService.updateTransport(courier,name, speed, weight, type);
            transport.ifPresent(value -> session.setAttribute(SessionAttribute.TRANSPORT, value));
            commandResult = new CommandResult(PagePath.COURIER_TRANSPORT, CommandResult.ResponseType.FORWARD);
        }catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
