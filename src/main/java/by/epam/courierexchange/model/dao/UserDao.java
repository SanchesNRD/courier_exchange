package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;

import java.util.Optional;

public interface UserDao extends BaseDao<Long, User>{
    Optional<User> selectByLogin(String login) throws DaoException;
    boolean uploadImgPath(long id, String path) throws DaoException;
    Optional<String> findImgPath(long id) throws DaoException;
    Optional<User> selectByMail(String mail) throws DaoException;
    int updatePasswordById(long id, String password) throws DaoException;
    int updateStatus(long id, UserStatus userStatus) throws DaoException;
}
