package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.connection.ConnectionPool;
import by.epam.courierexchange.model.dao.UserDao;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final UserDaoImpl instance = new UserDaoImpl();


    private static final String SQL_SELECT_ALL= """
            SELECT id, login, password, mail, name, surname, phone, image, status_id
            FROM users
            WHERE status_id!=5
            """;
    private static final String SQL_SELECT_BY_ID= """
            SELECT id, login, password, mail, name, surname, phone, image, status_id 
            FROM users WHERE id=?
            """;
    private static final String SQL_SELECT_BY_LOGIN= """
            SELECT id, login, password, mail, name, surname, phone, image, status_id 
            FROM users WHERE login=?
            """;
    private static final String SQL_SELECT_BY_MAIL= """
            SELECT id, login, password, mail, name, surname, phone, image, status_id 
            FROM users WHERE mail=?
            """;
    private static final String SQL_DELETE_BY_ID=
            "DELETE FROM users WHERE id=?";
    private static final String SQL_INSERT= """
            INSERT INTO users (id, login, password, mail, name, surname, phone, status_id) 
            VALUES (?,?,?,?,?,?,?,?)
            """;
    private static final String SQL_UPDATE= """
            UPDATE users SET login=?, password=?, mail=?, name=?, surname=?,
            phone=?, image=?, status_id=? WHERE id=?
            """;
    private static final String SQL_UPDATE_PASSWORD= """
            UPDATE users SET password=? WHERE id=?
            """;
    private static final String SQL_UPDATE_STATUS= """
            UPDATE users SET status_id=? WHERE id=?
            """;
    private static final String SQL_UPLOAD_IMG_PATH_BY_ID = """
            UPDATE users SET image=? WHERE id=?
            """;
    private static final String SQL_FIND_IMG_PATH_BY_ID = """
            SELECT image
            FROM users WHERE id=?
            """;

    private UserDaoImpl(){

    }

    public static UserDaoImpl getInstance(){
        return instance;
    }


    @Override
    public List<User> selectAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL))
        {
            while (resultSet.next()){
                User user = new User.UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setLogin(resultSet.getString(USER_LOGIN))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setMail(resultSet.getString(USER_MAIL))
                        .setName(resultSet.getString(USER_NAME))
                        .setSurname(resultSet.getString(USER_SURNAME))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();;
                users.add(user);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method selectAllUsers ", e);
            throw new DaoException("SQL exception in method selectAllUsers ", e);
        }
        return users;
    }

    @Override
    public Optional<User> selectById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else {
                User user = new User.UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setLogin(resultSet.getString(USER_LOGIN))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setMail(resultSet.getString(USER_MAIL))
                        .setName(resultSet.getString(USER_NAME))
                        .setSurname(resultSet.getString(USER_SURNAME))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectUserById ", e);
            throw new DaoException("SQL exception in method in selectUserById ", e);
        }
    }

    public Optional<User> selectByLogin(String login) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_LOGIN))
        {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else {
                User user = new User.UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setLogin(resultSet.getString(USER_LOGIN))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setMail(resultSet.getString(USER_MAIL))
                        .setName(resultSet.getString(USER_NAME))
                        .setSurname(resultSet.getString(USER_SURNAME))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectUserByLogin ", e);
            throw new DaoException("SQL exception in method in selectUserByLogin ", e);
        }
    }

    public Optional<User> selectByMail(String mail) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_MAIL))
        {
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else {
                User user = new User.UserBuilder()
                        .setId(resultSet.getLong(ID))
                        .setLogin(resultSet.getString(USER_LOGIN))
                        .setPassword(resultSet.getString(USER_PASSWORD))
                        .setMail(resultSet.getString(USER_MAIL))
                        .setName(resultSet.getString(USER_NAME))
                        .setSurname(resultSet.getString(USER_SURNAME))
                        .setPhone(resultSet.getString(USER_PHONE))
                        .setImage(resultSet.getString(USER_IMAGE))
                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();
                return Optional.of(user);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectUserByMail ", e);
            throw new DaoException("SQL exception in method in selectUserByMail ", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID))
        {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method in deleteUserById ", e);
            throw new DaoException("SQL exception in method in deleteUserById ", e);
        }
    }

    @Override
    public boolean create(User user) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getMail());
            statement.setString(5, user.getName());
            statement.setString(6, user.getSurname());
            statement.setString(7, user.getPhone());
            statement.setShort(8, user.getUserStatus().getStatusId());
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method createUser ", e);
            throw new DaoException("SQL exception in method createUser ", e);
        }
    }

    @Override
    public int update(User user) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE))
        {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSurname());
            statement.setString(6, user.getPhone());
            statement.setString(7, user.getImage());
            statement.setShort(8, user.getUserStatus().getStatusId());
            statement.setLong(9, user.getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateUser ", e);
            throw new DaoException("SQL exception in method updateUser ", e);
        }
    }

    @Override
    public int updatePasswordById(long id, String password) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD))
        {
            statement.setString(1, password);
            statement.setLong(2, id);
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateUser ", e);
            throw new DaoException("SQL exception in method updateUser ", e);
        }
    }

    @Override
    public boolean uploadImgPath(long id, String path) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPLOAD_IMG_PATH_BY_ID))
        {
            statement.setString(1, path);
            statement.setLong(2, id);
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method in uploadImgPath ", e);
            throw new DaoException("SQL exception in method in uploadImgPath ", e);
        }
    }

    @Override
    public Optional<String> findImgPath(long id) throws DaoException {
        String path = null;
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_FIND_IMG_PATH_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                path = resultSet.getString(USER_IMAGE);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in findImgPath ", e);
            throw new DaoException("SQL exception in method in findImgPath ", e);
        }
        return Optional.ofNullable(path);
    }

    @Override
    public int updateStatus(long id, UserStatus userStatus) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS))
        {
            statement.setShort(1, userStatus.getStatusId());
            statement.setLong(2, id);
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateUserStatus ", e);
            throw new DaoException("SQL exception in method updateUserStatus ", e);
        }
    }
}
