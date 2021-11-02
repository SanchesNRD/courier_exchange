package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
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
        TransportDaoImpl transportDao = TransportDaoImpl.getInstance();
        Optional<Transport> transport;
        HttpSession session = request.getSession();
        Courier courier = (Courier) session.getAttribute(SessionAttribute.COURIER);
        CommandResult commandResult;
        if(courier != null) {
            try {
                transport = transportDao.selectById(courier.getTransport());
                transport.ifPresent(value -> session.setAttribute(SessionAttribute.TRANSPORT, value));
                commandResult = new CommandResult(PagePath.COURIER_TRANSPORT, CommandResult.ResponseType.FORWARD);
            } catch (DaoException e) {
                request.setAttribute(EXCEPTION, e);
                commandResult = new CommandResult(ERROR_PAGE, FORWARD);
            }
        }else{
            commandResult = new CommandResult(PagePath.LOGIN_PAGE, CommandResult.ResponseType.FORWARD);
        }
        return commandResult;
    }
}
