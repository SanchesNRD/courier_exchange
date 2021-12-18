package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;


public class GoToAdminClientTemplates implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        List<ClientProduct> clientProducts;
        CommandResult commandResult;
        if (user != null && user.getUserStatus() == UserStatus.ADMIN) {
            try {
                clientProducts = clientDao.selectAllClientProduct();
                session.setAttribute(SessionAttribute.CLIENT_PRODUCT, clientProducts);
                commandResult = new CommandResult(PagePath.ADMIN_CLIENT_PRODUCTS, CommandResult.ResponseType.FORWARD);
            } catch (DaoException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        } else {
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
