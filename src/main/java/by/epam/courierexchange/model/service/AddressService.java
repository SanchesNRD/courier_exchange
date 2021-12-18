package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;


public interface AddressService {
    long createAddress(String country, String city, String street, String number, String apartment)
            throws ServiceException;
}
