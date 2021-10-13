package by.epam.courierexchange.controller.command.impl.client;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class UpdateClientAddress implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String country = request.getParameter(RequestParameter.COUNTRY);
        String city = request.getParameter(RequestParameter.CITY);
        String street = request.getParameter(RequestParameter.STREET);
        String apartment = request.getParameter(RequestParameter.APARTMENT);
        String street_number = request.getParameter(RequestParameter.STREET_NUMBER);
        HttpSession session = request.getSession();
        Client client = (Client)session.getAttribute(SessionAttribute.CLIENT)
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        CommandResult commandResult;
        try{
            clientService.updateAddress(client.getAddress(), country, city, street, street_number, apartment);
            commandResult = new CommandResult(PagePath.CLIENT_ADDRESS, CommandResult.ResponseType.FORWARD)
        }catch (ServiceException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
