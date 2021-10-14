package by.epam.courierexchange._main;

import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.AddressDaoImpl;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.UserDaoImpl;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.ProductServiceImpl;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import by.epam.courierexchange.model.validator.UserValidator;
import by.epam.courierexchange.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.controller.command.RequestParameter.*;
import static by.epam.courierexchange.controller.command.RequestParameter.PHONE;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String... args) {
        Optional<ClientProduct> optional;
        List<ClientProduct> list;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        Optional<Client> client;
        AddressDaoImpl addressDao = AddressDaoImpl.getInstance();
        ProductServiceImpl productService = ProductServiceImpl.getInstance();

        
    }
}
