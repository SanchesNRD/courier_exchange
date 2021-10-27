package by.epam.courierexchange.controller.command.impl.admin;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class DeleteProductCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String idProduct = request.getParameter(RequestParameter.ID);
        ProductServiceImpl productService = ProductServiceImpl.getInstance();
        HttpSession session = request.getSession();
        List<Product> products;
        CommandResult commandResult;
        int serviceResult;
        try {
            serviceResult = productService.deleteProduct(idProduct);
            if(serviceResult == 0){
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }else if(serviceResult == -1){
                request.setAttribute(RequestAttribute.PRODUCT_WAS_USED, true);
            }
            products = productService.selectAll();
            session.setAttribute(SessionAttribute.PRODUCTS, products);
            commandResult = new CommandResult(PagePath.ADMIN_PRODUCTS, CommandResult.ResponseType.FORWARD);
        } catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, FORWARD);
        }
        return commandResult;
    }
}
