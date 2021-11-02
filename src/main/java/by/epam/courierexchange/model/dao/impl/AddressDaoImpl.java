package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.connection.ConnectionPool;
import by.epam.courierexchange.model.dao.AddressDao;
import by.epam.courierexchange.model.entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class AddressDaoImpl implements AddressDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final AddressDaoImpl instance = new AddressDaoImpl();


    private static final String SQL_SELECT_ALL_ADDRESSES = """
            SELECT id, country, city, street, street_number, apartment
            FROM addresses
            """;
    private static final String SQL_SELECT_ADDRESS_BY_ID = """
            SELECT id, country, city, street, street_number, apartment 
            FROM addresses WHERE id=?
            """;
    private static final String SQL_DELETE_ADDRESS_BY_ID =
            "DELETE FROM addresses WHERE id=?";
    private static final String SQL_CREAT_ADDRESS= """
            INSERT INTO addresses (country, city, street, street_number, apartment)
            VALUES (?,?,?,?,?)""";
    private static final String SQL_UPDATE_ADDRESS= """
            UPDATE addresses SET country=?, city=?, street=?, street_number=?, apartment=?
            WHERE id=?
            """;
    private static final String SQL_SELECT_CLIENT_ADDRESS = """
            SELECT id FROM addresses 
            WHERE country=? AND city=? AND street=? AND street_number=? AND apartment=?
            """;

    private AddressDaoImpl(){}

    public static AddressDaoImpl getInstance(){
        return instance;
    }


    @Override
    public List<Address> selectAll() throws DaoException {
        List<Address> addresses = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ADDRESSES))
        {
            while (resultSet.next()){
                Address address = new Address.AddressBuilder()
                        .setId(resultSet.getLong(ID))
                        .setCountry(resultSet.getString(ADDRESS_COUNTRY))
                        .setCity(resultSet.getString(ADDRESS_CITY))
                        .setStreet(resultSet.getString(ADDRESS_STREET))
                        .setStreet_number(resultSet.getInt(ADDRESS_STREET_NUMBER))
                        .setApartment(resultSet.getInt(ADDRESS_APARTMENT))
                        .build();
                addresses.add(address);
            }
        } catch(SQLException e){
            logger.error("SQL exception in method selectAllAddress ", e);
            throw new DaoException("SQL exception in method selectAllAddress ", e);
        }
        return addresses;
    }

    @Override
    public Optional<Address> selectById(Long id) throws DaoException{
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ADDRESS_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else {
                Address address = new Address.AddressBuilder()
                        .setId(resultSet.getLong(ID))
                        .setCountry(resultSet.getString(ADDRESS_COUNTRY))
                        .setCity(resultSet.getString(ADDRESS_CITY))
                        .setStreet(resultSet.getString(ADDRESS_STREET))
                        .setStreet_number(resultSet.getInt(ADDRESS_STREET_NUMBER))
                        .setApartment(resultSet.getInt(ADDRESS_APARTMENT))
                        .build();
                return Optional.of(address);
            }
        } catch(SQLException e){
            logger.error("SQL exception in method selectAddressById ", e);
            throw new DaoException("SQL exception in method selectAddressById ", e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ADDRESS_BY_ID))
        {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method deleteAddressById ", e);
            throw new DaoException("SQL exception in method deleteAddressById ", e);
        }
    }

    @Override
    public int create(Address address) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_CREAT_ADDRESS))
        {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getStreet_number());
            statement.setInt(5, address.getApartment());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method createAddress ", e);
            throw new DaoException("SQL exception in method createAddress ", e);
        }
    }

    @Override
    public int update(Address address) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ADDRESS))
        {
            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setInt(4, address.getStreet_number());
            statement.setInt(5, address.getApartment());
            statement.setLong(6, address.getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateAddress ", e);
            throw new DaoException("SQL exception in method updateAddress ", e);
        }
    }

    @Override
    public long selectIdAddress(String country, String city, String street, Integer numberInt, Integer apartmentInt) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_CLIENT_ADDRESS))
        {
            statement.setString(1, country);
            statement.setString(2, city);
            statement.setString(3, street);
            statement.setInt(4, numberInt);
            statement.setInt(5, apartmentInt);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return 0;
            }else {
                return resultSet.getLong(ID);
            }
        } catch(SQLException e){
            logger.error("SQL exception in method selectAddressById ", e);
            throw new DaoException("SQL exception in method selectAddressById ", e);
        }
    }
}
