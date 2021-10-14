package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Address;

import java.util.List;

public interface AddressDao extends BaseDao<Long, Address>{
    List<Address> selectByCity(String patternCountry) throws DaoException;
    long selectIdAddress(String country,String city,String street,Integer numberInt,Integer apartmentInt)
            throws DaoException;
}
