package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Transport;

import java.util.List;

public interface TransportDao extends BaseDao<Long, Transport>{
    List<Transport> selectByType(Integer type) throws DaoException;
    List<Transport> selectBySpeed(Integer speed) throws DaoException;
    List<Transport> selectByWeight(Integer weight) throws DaoException;
    long selectIdTransport(String name, int speed, int weight, short type) throws DaoException;
}
