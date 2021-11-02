package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Courier;


public interface CourierDao extends BaseDao<Long, Courier>{
    boolean createById(long id) throws DaoException;
    int updateTransportToCourier(long courier, long transport) throws DaoException;
}
