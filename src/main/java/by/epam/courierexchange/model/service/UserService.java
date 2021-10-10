package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> authorization(String login, String password) throws ServiceException;
    Optional<User> registration(String login, String password, String name, String surname, String mail, String phone) throws ServiceException;
    boolean updatePassword(String id, String password) throws ServiceException;
    boolean uploadImgPath(long id, String path) throws ServiceException;
    String findImgPath(long id) throws ServiceException;
    Optional<User> updateProfile(String name, String surname, String phone, User user) throws ServiceException;
    Optional<User> changePassword(User user, String password, String newPassword) throws ServiceException;
    User confirmProfile(User user) throws ServiceException;
    Optional<User> changeRole(User user) throws ServiceException;
}
