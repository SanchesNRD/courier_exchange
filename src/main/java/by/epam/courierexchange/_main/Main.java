package by.epam.courierexchange._main;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.*;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.model.service.impl.ClientServiceImpl;
import by.epam.courierexchange.model.service.impl.ProductServiceImpl;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
        ProductDaoImpl productDao = ProductDaoImpl.getInstance();
        CourierDaoImpl courierDao = CourierDaoImpl.getInstance();
        TransportDaoImpl transportDao = TransportDaoImpl.getInstance();

        Date date = new Date();
        Object dateSql = new Timestamp(date.getTime());
        List<ClientProduct> clientProductList;
        Optional<ClientProduct> clientProductOptional;
        Optional<Product> productOptional;

        Optional<Transport> transport;
        Optional<Courier> courier;
        OrderStatus orderStatus = OrderStatus.COMPLETED;
        try {
            System.out.println(userService.registration("login", "a12345678", "name", "surname", "mail@mail.ru", "+375291234567"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
