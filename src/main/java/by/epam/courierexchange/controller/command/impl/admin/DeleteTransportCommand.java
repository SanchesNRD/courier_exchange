package by.epam.courierexchange.controller.command.impl.admin;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Transport;
import by.epam.courierexchange.model.service.impl.TransportServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class DeleteTransportCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idTransport = request.getParameter(RequestParameter.ID);
        TransportServiceImpl transportService = TransportServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<Transport> transports;
        CommandResult commandResult;
        int serviceResult;
        try {
            serviceResult = transportService.deleteTransport(idTransport);
            if(serviceResult == 0){
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }else if(serviceResult == -1){
                request.setAttribute(RequestAttribute.TRANSPORT_WAS_USED, true);
            }
            transports = transportService.selectAll();
            session.setAttribute(SessionAttribute.TRANSPORTS, transports);
            commandResult = new CommandResult(PagePath.ADMIN_TRANSPORTS, CommandResult.ResponseType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
