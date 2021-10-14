package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Address;

import java.util.Optional;

public interface AddressService {
    long createAddress(String country, String city, String street, String number, String apartment)
            throws ServiceException;
}
