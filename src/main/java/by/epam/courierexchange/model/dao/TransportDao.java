package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Transport;

import java.util.List;

public interface TransportDao extends BaseDao<Long, Transport>{
    long selectIdTransport(String name, int speed, int weight, short type) throws DaoException;
    List<Long> selectUsedProductById(long id) throws DaoException;
}
