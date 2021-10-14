package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.AddressDaoImpl;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.Address;
import by.epam.courierexchange.model.service.AddressService;
import by.epam.courierexchange.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AddressServiceImpl implements AddressService {
    private static final Logger logger = LogManager.getLogger();
    private static final AddressServiceImpl instance = new AddressServiceImpl();
    private static final AddressDaoImpl addressDao = AddressDaoImpl.getInstance();

    public AddressServiceImpl() {
    }

    public static AddressServiceImpl getInstance() {
        return instance;
    }

    @Override
    public long createAddress(String country, String city, String street, String number, String apartment)
            throws ServiceException {
        if(!UserValidator.nameIsValid(country) || !UserValidator.nameIsValid(city)
                || !UserValidator.nameIsValid(street) || !UserValidator.numberIsValid(number)
                || !UserValidator.numberIsValid(apartment)){
            return 0;
        }
        Address address;
        int numberInt = Integer.parseInt(number);
        int apartmentInt = Integer.parseInt(apartment);
        long idAddress;
        try{
            address = new Address.AddressBuilder()
                    .setCountry(country)
                    .setCity(city)
                    .setStreet(street)
                    .setStreet_number(numberInt)
                    .setApartment(apartmentInt)
                    .build();
            idAddress = addressDao.selectIdAddress(country, city, street, numberInt, apartmentInt);
            if(idAddress == 0){
                addressDao.create(address);
                idAddress = addressDao.selectIdAddress(country, city, street, numberInt, apartmentInt);
            }
            return idAddress;
        }catch (DaoException e){
            logger.error("DaoException to the create address: ", e);
            throw new ServiceException("DaoException to the create address: ", e);
        }
    }
}
