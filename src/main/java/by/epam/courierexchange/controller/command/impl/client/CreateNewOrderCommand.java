package by.epam.courierexchange.controller.command.impl.client;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.AddressServiceImpl;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.*;

public class CreateNewOrderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        AddressServiceImpl addressService = AddressServiceImpl.getInstance();
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        HttpSession session = request.getSession();
        CommandResult commandResult;
        String name = request.getParameter(RequestParameter.NAME);
        String weight = request.getParameter(RequestParameter.WEIGHT);
        String width = request.getParameter(RequestParameter.WIDTH);
        String height = request.getParameter(RequestParameter.HEIGHT);
        String length = request.getParameter(RequestParameter.LENGTH);
        String type = request.getParameter(RequestParameter.ORDER_TYPE);
        String country = request.getParameter(RequestParameter.COUNTRY);
        String city = request.getParameter(RequestParameter.CITY);
        String street = request.getParameter(RequestParameter.STREET);
        String number = request.getParameter(RequestParameter.STREET_NUMBER);
        String apartment = request.getParameter(RequestParameter.APARTMENT);
        try {
            long productId = productService.createProduct(name, weight, width, height, length, type);
            long addressId = addressService.createAddress(country, city, street, number, apartment);
            commandResult = new CommandResult(PagePath.NEW_ORDER_PAGE, FORWARD);

            if(productId != 0 && addressId != 0){
                User user = (User)session.getAttribute(SessionAttribute.USER);
                Optional<Client> clientOptional = clientDao.selectById(user.getId());
                if(clientOptional.isPresent()){
                    long clientAddress = clientOptional.get().getAddress();
                    if(clientAddress != addressId){
                        if(clientService.createProductClient(user.getId(), productId, addressId)==0){
                            request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
                        }
                    }
                    else{
                        request.setAttribute(RequestAttribute.SAME_ADDRESS, true);
                    }
                }
            }
            else{
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }
        } catch (ServiceException | DaoException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, FORWARD);
        }

        return commandResult;
    }
}
