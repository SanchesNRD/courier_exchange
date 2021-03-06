package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.connection.ConnectionPool;
import by.epam.courierexchange.model.dao.CourierDao;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.User;
import by.epam.courierexchange.model.entity.UserStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class CourierDaoImpl implements CourierDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final CourierDaoImpl instance = new CourierDaoImpl();

    private static final String SQL_SELECT_ALL_COURIER="""
           SELECT users.id, rating, transport_id, login, mail, 
            password, name, surname, phone, status_id 
            FROM couriers
            INNER JOIN users ON couriers.id = users.id
            """;
    private static final String SQL_SELECT_BY_ID="""
            SELECT users.id, rating, transport_id, login, mail, 
            password, name, surname, phone, status_id 
            FROM couriers
            INNER JOIN users ON couriers.id = users.id
            WHERE couriers.id=?
            """;
    private static final String SQL_DELETE_BY_ID=
            "DELETE FROM couriers WHERE id=?";
    private static final String SQL_INSERT=
            "INSERT INTO couriers (id, rating, transport_id) VALUES (?,?,?)";
    private static final String SQL_INSERT_BY_ID=
            "INSERT INTO couriers (id, rating) VALUES (?, ?)";
    private static final String SQL_UPDATE=
            "UPDATE couriers SET rating=?, transport_id=? WHERE id=?";
    private static final String SQL_UPDATE_TRANSPORT=
            "UPDATE couriers SET transport_id=? WHERE id=?";

    private CourierDaoImpl(){}

    public static CourierDaoImpl getInstance(){
        return instance;
    }

    @Override
    public List<Courier> selectAll() throws DaoException {
        List<Courier> couriers = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_COURIER))
        {
            while (resultSet.next()){
                Courier courier = new Courier.CourierBuilder()
                        .setRating(resultSet.getLong(COURIER_RATING))
                        .setTransport(resultSet.getLong(TRANSPORT_ID))
                        .setBuilder(new User.UserBuilder()
                                .setId(resultSet.getLong(USER_ID))
                                .setLogin(resultSet.getString(USER_LOGIN))
                                .setPassword(resultSet.getString(USER_PASSWORD))
                                .setMail(resultSet.getString(USER_MAIL))
                                .setName(resultSet.getString(USER_NAME))
                                .setSurname(resultSet.getString(USER_SURNAME))
                                .setPhone(resultSet.getString(USER_PHONE))
                                .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID))))
                        .build();
                couriers.add(courier);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectAllCourier ", e);
            throw new DaoException("SQL exception in method in selectAllCourier ", e);
        }
        return couriers;
    }

    @Override
    public Optional<Courier> selectById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else {
                Courier courier = new Courier.CourierBuilder()
                        .setRating(resultSet.getDouble(COURIER_RATING))
                        .setTransport(resultSet.getLong(TRANSPORT_ID))
                        .setBuilder(new User.UserBuilder()
                                .setId(resultSet.getLong(USER_ID))
                                .setLogin(resultSet.getString(USER_LOGIN))
                                .setPassword(resultSet.getString(USER_PASSWORD))
                                .setMail(resultSet.getString(USER_MAIL))
                                .setName(resultSet.getString(USER_NAME))
                                .setSurname(resultSet.getString(USER_SURNAME))
                                .setPhone(resultSet.getString(USER_PHONE))
                                .setUserStatus(UserStatus.parseStatus(resultSet.getShort(STATUS_ID))))
                        .build();
                return Optional.of(courier);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectCourierById ", e);
            throw new DaoException("SQL exception in method in selectCourierById ", e);
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
            logger.error("SQL exception in method in deleteCourierById ", e);
            throw new DaoException("SQL exception in method in deleteCourierById ", e);
        }
    }

    @Override
    public int create(Courier courier) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setLong(1, courier.getId());
            statement.setDouble(2, courier.getRating());
            statement.setLong(3, courier.getTransport());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method in createCourier ", e);
            throw new DaoException("SQL exception in method in createCourier ", e);
        }
    }

    @Override
    public boolean createById(long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BY_ID))
        {
            statement.setLong(1, id);
            statement.setDouble(2, 0);
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method in createCourier ", e);
            throw new DaoException("SQL exception in method in createCourier ", e);
        }
    }

    @Override
    public int update(Courier courier) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE))
        {
            statement.setDouble(1, courier.getRating());
            statement.setLong(2, courier.getTransport());
            statement.setLong(3, courier.getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method in updateCourier ", e);
            throw new DaoException("SQL exception in method in updateCourier ", e);
        }
    }

    @Override
    public int updateTransportToCourier(long courier, long transport) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_TRANSPORT))
        {
            statement.setLong(1, transport);
            statement.setLong(2, courier);
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateClient ", e);
            throw new DaoException("SQL exception in method updateClient ", e);
        }
    }
}
