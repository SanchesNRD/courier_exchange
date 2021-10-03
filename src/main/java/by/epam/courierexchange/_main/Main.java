package by.epam.courierexchange._main;

import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.controller.command.SessionAttribute;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.UserDaoImpl;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import by.epam.courierexchange.model.service.impl.UserServiceImpl;
import by.epam.courierexchange.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static by.epam.courierexchange.controller.command.RequestParameter.*;
import static by.epam.courierexchange.controller.command.RequestParameter.PHONE;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String... args) {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        String login = "Sanches";
        String password = "a12345678";
        String name = "Alex";
        String surname = "Gilep";
        String mail = "alex2906888@mail.ru";
        String phone = "+375293760885";
         User user = new User.UserBuilder()
                .setLogin(login)
                .setPassword(PasswordEncryption.encode(password))
                .setMail(mail)
                .setName(name)
                .setSurname(surname)
                .setPhone(phone)
                .setUserStatus(UserStatus.NON_CONFIRMED)
                .build();
        try {
            System.out.println(userDao.create(user));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
