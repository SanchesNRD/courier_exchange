package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Order;
import by.epam.courierexchange.model.entity.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends BaseDao<Long, Order>{
    int createByField(long clientProduct, long courier, Object date, OrderStatus status) throws DaoException;
    long selectIdByCourier(long courier, OrderStatus status) throws DaoException;
    Optional<Order> selectActiveOrderByCourier(long courier, OrderStatus status) throws DaoException;
    int updateStatus(long id, OrderStatus status) throws DaoException;
    List<Order> selectHistoryByCourier(long id, OrderStatus status) throws DaoException;
    List<Order> selectHistoryByClient(long id, OrderStatus status) throws DaoException;
    List<Long> selectOrderByUser(long id) throws DaoException;
}
