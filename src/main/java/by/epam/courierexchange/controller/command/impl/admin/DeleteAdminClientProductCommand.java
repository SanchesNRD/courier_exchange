package by.epam.courierexchange.controller.command.impl.admin;

import by.epam.courierexchange.controller.command.*;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class DeleteAdminClientProductCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        List<ClientProduct> clientProducts;
        CommandResult commandResult;
        try{
            if(clientService.deleteClientProduct(id)==0){
                request.setAttribute(RequestAttribute.WRONG_VALIDATION, true);
            }
            clientProducts = clientDao.selectAllClientProduct();
            session.setAttribute(SessionAttribute.CLIENT_PRODUCT, clientProducts);
            commandResult = new CommandResult(PagePath.ADMIN_CLIENT_PRODUCTS, CommandResult.ResponseType.FORWARD);
        }catch (ServiceException | DaoException e){
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
