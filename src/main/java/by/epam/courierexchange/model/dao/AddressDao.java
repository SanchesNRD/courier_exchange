package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Address;

public interface AddressDao extends BaseDao<Long, Address>{
    long selectIdAddress(String country,String city,String street,Integer numberInt,Integer apartmentInt)
            throws DaoException;
}
