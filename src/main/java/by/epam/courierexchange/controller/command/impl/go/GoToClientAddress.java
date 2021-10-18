package by.epam.courierexchange.controller.command.impl.go;

import by.epam.courierexchange.controller.command.Command;
import by.epam.courierexchange.controller.command.CommandResult;
import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.dao.impl.AddressDaoImpl;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.Address;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import static by.epam.courierexchange.controller.command.CommandResult.ResponseType.FORWARD;
import static by.epam.courierexchange.controller.command.PagePath.ERROR_PAGE;
import static by.epam.courierexchange.controller.command.RequestAttribute.EXCEPTION;

public class GoToClientAddress implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) {
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        AddressDaoImpl addressDao = AddressDaoImpl.getInstance();
        Optional<Client> client;
        Optional<Address> address;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        CommandResult commandResult;
        if(user != null) {
            try {
                client = clientDao.selectById(user.getId());
                if(client.isPresent()){
                    session.setAttribute(SessionAttribute.CLIENT, client.get());
                    long addressId = client.get().getAddress();
                    address = addressDao.selectById(addressId);
                    address.ifPresent(value -> session.setAttribute(SessionAttribute.ADDRESS, value));
                }
                commandResult =  new CommandResult(PagePath.CLIENT_ADDRESS, FORWARD);
            } catch (DaoException e) {
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