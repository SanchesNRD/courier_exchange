package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.OrderDaoImpl;
import by.epam.courierexchange.model.dao.impl.UserDaoImpl;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import by.epam.courierexchange.model.service.UserService;
import by.epam.courierexchange.model.validator.CourierExchangeValidator;
import by.epam.courierexchange.util.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static final UserServiceImpl instance = new UserServiceImpl();
    private static final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private static final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
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
        if (CourierExchangeValidator.loginIsInvalid(login) || CourierExchangeValidator.passwordIsInvalid(password)){
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
    public int registration(String login, String password, String name, String surname, String mail, String phone)
            throws ServiceException {
        User user;
        if(CourierExchangeValidator.loginIsInvalid(login) || CourierExchangeValidator.passwordIsInvalid(password)
                || CourierExchangeValidator.emailIsInvalid(mail) || CourierExchangeValidator.nameIsInvalid(name)
                || CourierExchangeValidator.surnameIsInvalid(surname) || CourierExchangeValidator.phoneIsInvalid(phone)){
            return 0;
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
            return userDao.create(user);
        } catch (DaoException e){
            logger.error("Exception wile working with database ", e);
            throw new ServiceException("Exception wile working with database ", e);
        }
    }

    @Override
    public boolean updatePassword(String id, String password) throws ServiceException {
        if(CourierExchangeValidator.passwordIsInvalid(password) && CourierExchangeValidator.numberIsInvalid(id)){
            return false;
        }
        try{
            return userDao.updatePasswordById(Long.parseLong(id), PasswordEncryption.encode(password)) == 1;
        }catch (DaoException e){
            logger.error("Exception while working with the database ", e);
            throw new ServiceException("exception while working with the database ", e);
        }
    }

    public Optional<User> selectByMail(String mail) throws ServiceException{
        if (CourierExchangeValidator.emailIsInvalid(mail)){
            return Optional.empty();
        }
        try{
            return userDao.selectByMail(mail);
        }catch (DaoException e){
            logger.error("Exception while working with the database ", e);
            throw new ServiceException("exception while working with the database ", e);
        }
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

    @Override
    public Optional<User> changePassword(User user, String password, String newPassword) throws ServiceException {
        if(CourierExchangeValidator.passwordIsInvalid(password) || CourierExchangeValidator.passwordIsInvalid(newPassword)){
            return Optional.empty();
        }
        if(!user.getPassword().equals(PasswordEncryption.encode(password))){
            return Optional.empty();
        }
        try {
            userDao.updatePasswordById(user.getId(), PasswordEncryption.encode(newPassword));
            user = new User.UserBuilder()
                    .setPassword(PasswordEncryption.encode(newPassword))
                    .setId(user.getId())
                    .setImage(user.getImage())
                    .setLogin(user.getLogin())
                    .setMail(user.getMail())
                    .setName(user.getName())
                    .setSurname(user.getSurname())
                    .setUserStatus(user.getUserStatus())
                    .setPhone(user.getPhone())
                    .build();
            return Optional.of(user);
        } catch (DaoException e) {
            logger.error("DaoException to the update password: ", e);
            throw new ServiceException("DaoException to the update password: ", e);
        }
    }

    @Override
    public Optional<User> updateProfile(String name, String surname, String phone, User user) throws ServiceException {
        Optional<User> optionalUser;
        if(CourierExchangeValidator.nameIsInvalid(name) || CourierExchangeValidator.surnameIsInvalid(surname)
                || CourierExchangeValidator.phoneIsInvalid(phone)){
            return Optional.empty();
        }
        try{
            user = new User.UserBuilder()
                    .setPhone(phone)
                    .setSurname(surname)
                    .setName(name)
                    .setMail(user.getMail())
                    .setLogin(user.getLogin())
                    .setPassword(user.getPassword())
                    .setId(user.getId())
                    .setImage(user.getImage())
                    .setUserStatus(user.getUserStatus())
                    .build();
            userDao.update(user);
            optionalUser = Optional.of(user);
        } catch (DaoException e){
            logger.error("DaoException to the update profile: ", e);
            throw new ServiceException("DaoException to the update profile: ", e);
        }
        return optionalUser;
    }


    @Override
    public Optional<User> changeConfirmedRole(User user) throws ServiceException {
        if(user.getUserStatus() != UserStatus.COURIER_CONFIRMED && user.getUserStatus() != UserStatus.CONFIRMED) {
            return Optional.empty();
        }
        try{
            UserStatus userStatus;
            if(user.getUserStatus() == UserStatus.COURIER_CONFIRMED){
               userStatus = UserStatus.CONFIRMED;
            }else{
                userStatus = UserStatus.COURIER_CONFIRMED;
            }
            userDao.updateStatus(user.getId(), userStatus);
            return userDao.selectById(user.getId());
        } catch (DaoException e){
            logger.error("DaoException to the update user status: ", e);
            throw new ServiceException("DaoException to the update user status: ", e);
        }
    }

    @Override
    public int changeRole(String id, UserStatus userStatus) throws ServiceException {
        if(CourierExchangeValidator.numberIsInvalid(id)) {
            return 0;
        }
        try{
            long userId = Long.parseLong(id);
            return userDao.updateStatus(userId, userStatus);
        } catch (DaoException e){
            logger.error("DaoException to the update user status: ", e);
            throw new ServiceException("DaoException to the update user status: ", e);
        }
    }

    @Override
    public int banUser(String idStr) throws ServiceException {
        if(CourierExchangeValidator.numberIsInvalid(idStr)){
            return 0;
        }
        int result;
        long id = Long.parseLong(idStr);
        Optional<User> userOptional;
        List<Long> orders;
        try {
            userOptional = userDao.selectById(id);
            if(userOptional.isEmpty()) {
                return 0;
            }
            User user = userOptional.get();
            if(user.getUserStatus() == UserStatus.ADMIN){
                return 0;
            }
            if(user.getUserStatus() == UserStatus.BANED){
                result = userDao.updateStatus(id, UserStatus.NON_CONFIRMED);
            }
            else {
                orders = orderDao.selectOrderByUser(id);
                if (orders.size() == 0) {
                    result = userDao.updateStatus(id, UserStatus.BANED);
                    clientDao.deleteClientProductByClient(id);
                } else {
                    result = -1;
                }
            }
        } catch (DaoException e) {
            logger.error("DaoException to the update user status: ", e);
            throw new ServiceException("DaoException to the update user status: ", e);
        }
        return result;
    }

    @Override
    public List<User> selectAll() throws ServiceException {
        try {
            return userDao.selectAll();
        } catch (DaoException e) {
            logger.error("DaoException to the select all users: ", e);
            throw new ServiceException("DaoException to the select all users: ", e);
        }
    }
}
