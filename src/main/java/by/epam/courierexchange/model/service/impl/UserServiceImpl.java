package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.controller.command.PagePath;
import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.UserDaoImpl;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import by.epam.courierexchange.model.service.UserService;
import by.epam.courierexchange.model.validator.UserValidator;
import by.epam.courierexchange.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();
    private static final UserDaoImpl userDao = UserDaoImpl.getInstance();
    private static final String DEFAULT_PATH="C:\\Users\\alex2\\epam\\img\\";
    private static final String DEFAULT_IMG = "default.jpg";

    public UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }


    @Override
    public Optional<User> authorization(String login, String password) throws ServiceException {
        Optional<User> optionalUser;
        User user;
        if (!UserValidator.loginIsValid(login) && !UserValidator.passwordIsValid(password)){
            return Optional.empty();
        }

        try {
            optionalUser = userDao.selectByLogin(login);
            if(optionalUser.isEmpty()){
                return Optional.empty();
            }
            user = optionalUser.get();
            String plainPass = user.getPassword();
            String hashedPass = PasswordEncryption.encode(password);
            if(!PasswordEncryption.matches(plainPass, hashedPass)){
                return Optional.empty();
            }
        } catch (DaoException e) {
            logger.error("Exception while working with the database ", e);
            throw new ServiceException("exception while working with the database ", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<User> registration(String login, String password, String name, String surname, String mail, String phone)
            throws ServiceException {
        boolean createResult;
        Optional<User> optionalUser;
        User user;
        if(!UserValidator.loginIsValid(login) && !UserValidator.passwordIsValid(password)
                && !UserValidator.emailIsValid(mail) && !UserValidator.nameIsValid(name)
                && !UserValidator.surnameIsValid(surname) && !UserValidator.phoneIsValid(phone)){
            return Optional.empty();
        }
        try{
            user = new User.UserBuilder()
                    .setLogin(login)
                    .setPassword(PasswordEncryption.encode(password))
                    .setMail(mail)
                    .setName(name)
                    .setSurname(surname)
                    .setPhone(phone)
                    .setUserStatus(UserStatus.NON_CONFIRMED)
                    .build();
            createResult = userDao.create(user);
            optionalUser = Optional.of(user);
        } catch (DaoException e){
            logger.error("Exception wile working with database ", e);
            throw new ServiceException("Exception wile working with database ", e);
        }
        return optionalUser;
    }

    @Override
    public boolean updatePassword(User user) throws ServiceException {
        // TODO: 11.09.2021 !!!
        return false;
    }

    public boolean uploadImgPath(long id, String name) throws ServiceException{
        try {
            return userDao.uploadImgPath(id, name);
        } catch (DaoException e){
            logger.error("DaoException to the upload file: ", e);
            throw new ServiceException("DaoException to the upload file: ", e);
        }
    }

    @Override
    public String findImgPath(long id) throws ServiceException {
        try{
            Optional<String> optionalPath;
            String path;
            optionalPath = userDao.findImgPath(id);
            if(optionalPath.isEmpty()){
                path = DEFAULT_PATH + DEFAULT_IMG;
            }else{
                path = DEFAULT_PATH + optionalPath.get();
            }
            return path;
        } catch (DaoException e){
            logger.error("DaoException to the find file path: ", e);
            throw new ServiceException("DaoException to the find file path: ", e);
        }
    }
}
