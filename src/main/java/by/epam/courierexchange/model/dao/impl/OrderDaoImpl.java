package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.connection.ConnectionPool;
import by.epam.courierexchange.model.dao.OrderDao;
import by.epam.courierexchange.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final OrderDaoImpl instance = new OrderDaoImpl();

    private static final String SQL_SELECT_ALL="""
            SELECT id, client_product_id, courier_id, date, status_id 
            FROM orders
            """;
    private static final String SQL_SELECT_BY_ID="""
            SELECT id, client_product_id, courier_id, date, status_id 
            FROM orders WHERE id=?
            """;
    private static final String SQL_SELECT_BY_COURIER="""
            SELECT id, client_product_id, courier_id, date, status_id 
            FROM orders WHERE courier_id=? AND status_id=?
            """;
    private static final String SQL_DELETE_BY_ID=
            "DELETE FROM orders WHERE id=?";
    private static final String SQL_INSERT=""" 
            INSERT INTO orders(client_product_id, courier_id, date, status_id) 
            VALUES (?,?,?,?)
            """;
    private static final String SQL_UPDATE="""
            UPDATE orders SET client_product_id=?, courier_id=?, date=?, status_id=? 
            WHERE id=?
            """;
    private static final String SQL_UPDATE_STATUS="""
            UPDATE orders SET status_id=? 
            WHERE id=?
            """;
    private static final String SQL_SELECT_ACTIVE_BY_COURIER="""
            SELECT orders.id, orders.date, orders.status_id, courier_id, co.transport_id, co.rating,
                  u2.name, u2.surname, u2.login, u2.password, u2.image, u2.mail, u2.phone, u2.status_id,
                  client_product_id, client_id, cp.address_id,
                  u.name, u.surname, u.login, u.password, u.image, u.mail, u.phone, u.status_id,
                  product_id, p.name, p.height, p.width, p.length, p.weight, p.type_id,
                  cl.address_id, a.country, a.city, a.street, a.street_number, a.apartment
            FROM orders
                  INNER JOIN couriers co on orders.courier_id = co.id
                  INNER JOIN client_product cp on orders.client_product_id = cp.id
                  INNER JOIN products p on cp.product_id = p.id
                  INNER JOIN clients cl on cp.client_id = cl.id
                  INNER JOIN users u on cl.id = u.id
                  INNER JOIN users u2 on co.id = u2.id
                  INNER JOIN addresses a on cp.address_id = a.id
            WHERE courier_id=? AND orders.status_id=?
            """;
    private static final String SQL_SELECT_ACTIVE_BY_CLIENT="""
            SELECT orders.id, orders.date, orders.status_id, courier_id, co.transport_id, co.rating,
                  u2.name, u2.surname, u2.login, u2.password, u2.image, u2.mail, u2.phone, u2.status_id,
                  client_product_id, client_id, cp.address_id,
                  u.name, u.surname, u.login, u.password, u.image, u.mail, u.phone, u.status_id,
                  product_id, p.name, p.height, p.width, p.length, p.weight, p.type_id,
                  cl.address_id, a.country, a.city, a.street, a.street_number, a.apartment
            FROM orders
                  INNER JOIN couriers co on orders.courier_id = co.id
                  INNER JOIN client_product cp on orders.client_product_id = cp.id
                  INNER JOIN products p on cp.product_id = p.id
                  INNER JOIN clients cl on cp.client_id = cl.id
                  INNER JOIN users u on cl.id = u.id
                  INNER JOIN users u2 on co.id = u2.id
                  INNER JOIN addresses a on cp.address_id = a.id
            WHERE client_id=? AND orders.status_id=?
            """;
    private static final String SQL_SELECT_ACTIVE_BY_USER="""
            SELECT orders.id
            FROM orders
                  INNER JOIN client_product cp on orders.client_product_id = cp.id
            WHERE client_id=? OR courier_id=? AND status_id!=?
            """;

    private OrderDaoImpl(){}

    public static OrderDaoImpl getInstance(){
        return instance;
    }

    @Override
    public List<Order> selectAll() throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL))
        {
            while(resultSet.next()){
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ID))
                        .setClientProduct(new ClientProduct())
                        .setCourier(new Courier.CourierBuilder()
                                .build())
                        .setDate(resultSet.getDate(ORDER_DATE))
                        .setOrderStatus(OrderStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();
                orders.add(order);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectAllOrders ", e);
            throw new DaoException("SQL exception in method in selectAllOrders ", e);
        }
        return orders;
    }

    @Override
    public Optional<Order> selectById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else{
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ID))
                        .setClientProduct(new ClientProduct())
                        .setCourier(new Courier.CourierBuilder()
                                .build())
                        .setDate(resultSet.getDate(ORDER_DATE))
                        .setOrderStatus(OrderStatus.parseStatus(resultSet.getShort(STATUS_ID)))
                        .build();
                return Optional.of(order);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderById ", e);
            throw new DaoException("SQL exception in method in selectOrderById ", e);
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
            logger.error("SQL exception in method in deleteOrder ", e);
            throw new DaoException("SQL exception in method in deleteOrder ", e);
        }
    }

    @Override
    public boolean create(Order order) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setLong(1, order.getId());
            statement.setLong(2, order.getClientProduct().getId());
            statement.setLong(3, order.getCourier().getId());
            statement.setDate(4, order.getDate());
            statement.setShort(5, order.getOrderStatus().getStatusId());
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method in createOrder ", e);
            throw new DaoException("SQL exception in method in createOrder ", e);
        }
    }

    @Override
    public int createByField(long clientProduct, long courier, Object date, OrderStatus status) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setLong(1, clientProduct);
            statement.setLong(2, courier);
            statement.setObject(3, date);
            statement.setShort(4, status.getStatusId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method in createOrder ", e);
            throw new DaoException("SQL exception in method in createOrder ", e);
        }
    }

    @Override
    public int update(Order order) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE))
        {
            statement.setLong(1, order.getClientProduct().getId());
            statement.setLong(2, order.getCourier().getId());
            statement.setDate(3, order.getDate());
            statement.setShort(4, order.getOrderStatus().getStatusId());
            statement.setLong(5, order.getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method in updateOrder ", e);
            throw new DaoException("SQL exception in method in updateOrder ", e);
        }
    }

    @Override
    public long selectIdByCourier(long courier, OrderStatus status) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_COURIER))
        {
            statement.setLong(1, courier);
            statement.setShort(2, status.getStatusId());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return 0;
            }else{
                return resultSet.getLong(ID);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderById ", e);
            throw new DaoException("SQL exception in method in selectOrderById ", e);
        }
    }

    @Override
    public Optional<Order> selectActiveOrderByCourier(long courier, OrderStatus status) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACTIVE_BY_COURIER))
        {
            statement.setLong(1, courier);
            statement.setShort(2, status.getStatusId());
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            }else{
                ClientProduct clientProduct = new ClientProduct();
                clientProduct.setId(resultSet.getLong(ORDER_CLIENT_PRODUCT_ID));
                clientProduct.setClient(new Client.ClientBuilder()
                        .setAddress(resultSet.getLong(CL_ADDRESS_ID))
                        .setBuilder(new User.UserBuilder()
                            .setId(resultSet.getLong(CLIENT_ID))
                            .setName(resultSet.getString(U_NAME))
                            .setSurname(resultSet.getString(U_SURNAME))
                            .setLogin(resultSet.getString(U_LOGIN))
                            .setPassword(resultSet.getString(U_PASSWORD))
                            .setMail(resultSet.getString(U_MAIL))
                            .setImage(resultSet.getString(U_IMAGE))
                            .setPhone(resultSet.getString(U_PHONE))
                            .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U_STATUS))))
                        .build());
                clientProduct.setAddress(new Address.AddressBuilder()
                        .setId(resultSet.getInt(CP_ADDRESS))
                        .setCountry(resultSet.getString(A_COUNTRY))
                        .setCity(resultSet.getString(A_CITY))
                        .setStreet(resultSet.getString(A_STREET))
                        .setStreet_number(resultSet.getInt(A_STREET_NUMBER))
                        .setApartment(resultSet.getInt(A_APARTMENT))
                        .build());
                clientProduct.setProduct(new Product.ProductBuilder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(P_NAME))
                        .setWidth(resultSet.getInt(P_WIDTH))
                        .setHeight(resultSet.getInt(P_HEIGHT))
                        .setWeight(resultSet.getInt(P_WEIGHT))
                        .setLength(resultSet.getInt(P_LENGTH))
                        .setProductType(ProductType.parseType(resultSet.getShort(P_TYPE)))
                        .build());
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ORDERS_ID))
                        .setClientProduct(clientProduct)
                        .setCourier(new Courier.CourierBuilder()
                                .setBuilder(new User.UserBuilder()
                                        .setId(resultSet.getLong(COURIER_ID))
                                        .setName(resultSet.getString(U2_NAME))
                                        .setSurname(resultSet.getString(U2_SURNAME))
                                        .setLogin(resultSet.getString(U2_LOGIN))
                                        .setPassword(resultSet.getString(U2_PASSWORD))
                                        .setMail(resultSet.getString(U2_MAIL))
                                        .setImage(resultSet.getString(U2_IMAGE))
                                        .setPhone(resultSet.getString(U2_PHONE))
                                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U2_STATUS))))
                                .setTransport(resultSet.getLong(CO_TRANSPORT))
                                .setRating(resultSet.getDouble(CO_RATING))
                                .build())
                        .setDate(resultSet.getDate(ORDERS_DATE))
                        .setOrderStatus(OrderStatus.parseStatus(resultSet.getShort(ORDERS_STATUS)))
                        .build();
                return Optional.of(order);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderById ", e);
            throw new DaoException("SQL exception in method in selectOrderById ", e);
        }
    }

    @Override
    public int updateStatus(long id, OrderStatus status) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_STATUS))
        {
            statement.setShort(1, status.getStatusId());
            statement.setLong(2, id);
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method in updateOrderStatus ", e);
            throw new DaoException("SQL exception in method in updateOrderSatus ", e);
        }
    }

    @Override
    public List<Order> selectHistoryByCourier(long id, OrderStatus status) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACTIVE_BY_COURIER))
        {
            statement.setLong(1, id);
            statement.setShort(2, status.getStatusId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                ClientProduct clientProduct = new ClientProduct();
                clientProduct.setId(resultSet.getLong(ORDER_CLIENT_PRODUCT_ID));
                clientProduct.setClient(new Client.ClientBuilder()
                        .setAddress(resultSet.getLong(CL_ADDRESS_ID))
                        .setBuilder(new User.UserBuilder()
                                .setId(resultSet.getLong(CLIENT_ID))
                                .setName(resultSet.getString(U_NAME))
                                .setSurname(resultSet.getString(U_SURNAME))
                                .setLogin(resultSet.getString(U_LOGIN))
                                .setPassword(resultSet.getString(U_PASSWORD))
                                .setMail(resultSet.getString(U_MAIL))
                                .setImage(resultSet.getString(U_IMAGE))
                                .setPhone(resultSet.getString(U_PHONE))
                                .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U_STATUS))))
                        .build());
                clientProduct.setAddress(new Address.AddressBuilder()
                        .setId(resultSet.getInt(CP_ADDRESS))
                        .setCountry(resultSet.getString(A_COUNTRY))
                        .setCity(resultSet.getString(A_CITY))
                        .setStreet(resultSet.getString(A_STREET))
                        .setStreet_number(resultSet.getInt(A_STREET_NUMBER))
                        .setApartment(resultSet.getInt(A_APARTMENT))
                        .build());
                clientProduct.setProduct(new Product.ProductBuilder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(P_NAME))
                        .setWidth(resultSet.getInt(P_WIDTH))
                        .setHeight(resultSet.getInt(P_HEIGHT))
                        .setWeight(resultSet.getInt(P_WEIGHT))
                        .setLength(resultSet.getInt(P_LENGTH))
                        .setProductType(ProductType.parseType(resultSet.getShort(P_TYPE)))
                        .build());
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ORDERS_ID))
                        .setClientProduct(clientProduct)
                        .setCourier(new Courier.CourierBuilder()
                                .setBuilder(new User.UserBuilder()
                                        .setId(resultSet.getLong(COURIER_ID))
                                        .setName(resultSet.getString(U2_NAME))
                                        .setSurname(resultSet.getString(U2_SURNAME))
                                        .setLogin(resultSet.getString(U2_LOGIN))
                                        .setPassword(resultSet.getString(U2_PASSWORD))
                                        .setMail(resultSet.getString(U2_MAIL))
                                        .setImage(resultSet.getString(U2_IMAGE))
                                        .setPhone(resultSet.getString(U2_PHONE))
                                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U2_STATUS))))
                                .setTransport(resultSet.getLong(CO_TRANSPORT))
                                .setRating(resultSet.getDouble(CO_RATING))
                                .build())
                        .setDate(resultSet.getDate(ORDERS_DATE))
                        .setOrderStatus(OrderStatus.parseStatus(resultSet.getShort(ORDERS_STATUS)))
                        .build();
                orders.add(order);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderById ", e);
            throw new DaoException("SQL exception in method in selectOrderById ", e);
        }
        return orders;
    }

    @Override
    public List<Order> selectHistoryByClient(long id, OrderStatus status) throws DaoException {
        List<Order> orders = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACTIVE_BY_CLIENT))
        {
            statement.setLong(1, id);
            statement.setShort(2, status.getStatusId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                ClientProduct clientProduct = new ClientProduct();
                clientProduct.setId(resultSet.getLong(ORDER_CLIENT_PRODUCT_ID));
                clientProduct.setClient(new Client.ClientBuilder()
                        .setAddress(resultSet.getLong(CL_ADDRESS_ID))
                        .setBuilder(new User.UserBuilder()
                                .setId(resultSet.getLong(CLIENT_ID))
                                .setName(resultSet.getString(U_NAME))
                                .setSurname(resultSet.getString(U_SURNAME))
                                .setLogin(resultSet.getString(U_LOGIN))
                                .setPassword(resultSet.getString(U_PASSWORD))
                                .setMail(resultSet.getString(U_MAIL))
                                .setImage(resultSet.getString(U_IMAGE))
                                .setPhone(resultSet.getString(U_PHONE))
                                .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U_STATUS))))
                        .build());
                clientProduct.setAddress(new Address.AddressBuilder()
                        .setId(resultSet.getInt(CP_ADDRESS))
                        .setCountry(resultSet.getString(A_COUNTRY))
                        .setCity(resultSet.getString(A_CITY))
                        .setStreet(resultSet.getString(A_STREET))
                        .setStreet_number(resultSet.getInt(A_STREET_NUMBER))
                        .setApartment(resultSet.getInt(A_APARTMENT))
                        .build());
                clientProduct.setProduct(new Product.ProductBuilder()
                        .setId(resultSet.getLong(PRODUCT_ID))
                        .setName(resultSet.getString(P_NAME))
                        .setWidth(resultSet.getInt(P_WIDTH))
                        .setHeight(resultSet.getInt(P_HEIGHT))
                        .setWeight(resultSet.getInt(P_WEIGHT))
                        .setLength(resultSet.getInt(P_LENGTH))
                        .setProductType(ProductType.parseType(resultSet.getShort(P_TYPE)))
                        .build());
                Order order = new Order.OrderBuilder()
                        .setId(resultSet.getLong(ORDERS_ID))
                        .setClientProduct(clientProduct)
                        .setCourier(new Courier.CourierBuilder()
                                .setBuilder(new User.UserBuilder()
                                        .setId(resultSet.getLong(COURIER_ID))
                                        .setName(resultSet.getString(U2_NAME))
                                        .setSurname(resultSet.getString(U2_SURNAME))
                                        .setLogin(resultSet.getString(U2_LOGIN))
                                        .setPassword(resultSet.getString(U2_PASSWORD))
                                        .setMail(resultSet.getString(U2_MAIL))
                                        .setImage(resultSet.getString(U2_IMAGE))
                                        .setPhone(resultSet.getString(U2_PHONE))
                                        .setUserStatus(UserStatus.parseStatus(resultSet.getShort(U2_STATUS))))
                                .setTransport(resultSet.getLong(CO_TRANSPORT))
                                .setRating(resultSet.getDouble(CO_RATING))
                                .build())
                        .setDate(resultSet.getDate(ORDERS_DATE))
                        .setOrderStatus(OrderStatus.parseStatus(resultSet.getShort(ORDERS_STATUS)))
                        .build();
                orders.add(order);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderById ", e);
            throw new DaoException("SQL exception in method in selectOrderById ", e);
        }
        return orders;
    }

    @Override
    public List<Long> selectOrderByUser(long id) throws DaoException {
        List<Long> orders = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ACTIVE_BY_USER))
        {
            statement.setLong(1, id);
            statement.setLong(2, id);
            statement.setShort(3, OrderStatus.COMPLETED.getStatusId());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                orders.add(resultSet.getLong(ORDERS_ID));
            }
        } catch (SQLException e){
            logger.error("SQL exception in method in selectOrderByUser ", e);
            throw new DaoException("SQL exception in method in selectOrderByUser ", e);
        }
        return orders;
    }
}
