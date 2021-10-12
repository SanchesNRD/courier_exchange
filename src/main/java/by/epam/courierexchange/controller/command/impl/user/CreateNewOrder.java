package by.epam.courierexchange.controller.command.impl.user;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.*;

public class CreateNewOrder implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        HttpSession session = request.getSession();
        CommandResult commandResult;
        String name = request.getParameter(RequestParameter.NAME);
        String weight = request.getParameter(RequestParameter.WEIGHT);
        String width = request.getParameter(RequestParameter.WIDTH);
        String height = request.getParameter(RequestParameter.HEIGHT);
        String length = request.getParameter(RequestParameter.LENGTH);
        String type = request.getParameter(RequestParameter.ORDER_TYPE);
        try {
            productService.createProduct(name, weight, width, height, length, type);
            commandResult = new CommandResult(PagePath.NEW_ORDER_PAGE, FORWARD);
            Optional<Product> optionalProduct;
            optionalProduct = productService.findProductByName(name);
            if(optionalProduct.isPresent()){
                Product product = optionalProduct.get();
                User user = (User)session.getAttribute(SessionAttribute.USER);
                clientService.createProductClient(user.getId(), product.getId(), 1l);
            }
            else{
                return new CommandResult(PagePath.ERROR_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            return new CommandResult(PagePath.ERROR_PAGE, FORWARD);
        }

        return commandResult;
    }
}
