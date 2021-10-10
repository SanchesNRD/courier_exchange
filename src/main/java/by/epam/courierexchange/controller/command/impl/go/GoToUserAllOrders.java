package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;

public class GoToUserAllOrders implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        List<ClientProduct> clientProducts;
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionAttribute.USER) != null) {
            try {
                clientProducts = clientDao.selectAllClientProduct();
                // TODO: 06.10.2021 show full info about product and client
                session.setAttribute("clientProducts", clientProducts);
                for (ClientProduct clientProduct: clientProducts) {

                }
            } catch (DaoException e) {
                return new CommandResult(PagePath.ERROR_PAGE, FORWARD);
            }
            return new CommandResult(PagePath.USER_ALL_ORDERS_PAGE, FORWARD);
        }
        else{
            return new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
    }
}
