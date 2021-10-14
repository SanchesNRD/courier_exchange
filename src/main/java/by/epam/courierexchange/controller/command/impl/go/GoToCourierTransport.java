package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.CourierDaoImpl;
import by.epam.courierexchange.model.dao.impl.TransportDaoImpl;
import by.epam.courierexchange.model.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToCourierTransport implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        CourierDaoImpl courierDao = CourierDaoImpl.getInstance();
        TransportDaoImpl transportDao = TransportDaoImpl.getInstance();
        Optional<Courier> courier;
        Optional<Transport> transport;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        CommandResult commandResult;
        if(user != null) {
            try {
                courier = courierDao.selectById(user.getId());
                if(courier.isPresent()){
                    session.setAttribute(SessionAttribute.COURIER, courier.get());
                    long transportId = courier.get().getTransport();
                    transport = transportDao.selectById(transportId);
                    transport.ifPresent(value -> session.setAttribute(SessionAttribute.TRANSPORT, value));
                }else{
                    courierDao.createById(user.getId());
                    courier = courierDao.selectById(user.getId());
                    courier.ifPresent(value -> session.setAttribute(SessionAttribute.COURIER, value));
                }
                commandResult = new CommandResult(PagePath.COURIER_TRANSPORT, CommandResult.ResponseType.FORWARD);
            } catch (DaoException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        }else{
            commandResult = new CommandResult(PagePath.ERROR_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
