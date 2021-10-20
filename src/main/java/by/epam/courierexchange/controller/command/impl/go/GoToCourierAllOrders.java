package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.CourierDaoImpl;
import by.epam.courierexchange.model.dao.impl.TransportDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToCourierAllOrders implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        CourierDaoImpl courierDao = CourierDaoImpl.getInstance();
        Optional<Courier> courier;
        List<ClientProduct> clientProducts;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        CommandResult commandResult;
        if(user != null) {
            try {
                courier = courierDao.selectById(user.getId());
                if(courier.isPresent()){
                    session.setAttribute(SessionAttribute.COURIER, courier.get());
                }else{
                    courierDao.createById(user.getId());
                    courier = courierDao.selectById(user.getId());
                    courier.ifPresent(value -> session.setAttribute(SessionAttribute.COURIER, value));
                }
                clientProducts = clientService.selectClientProductForCourier(courier.get().getId(), courier.get().getTransport());
                session.setAttribute(SessionAttribute.CLIENT_PRODUCT, clientProducts);
                commandResult = new CommandResult(PagePath.COURIER_ALL_ORDERS_PAGE, FORWARD);
            } catch (DaoException | ServiceException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        }
        else{
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, FORWARD);
        }
        return commandResult;
    }
}
