package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.AddressDaoImpl;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.dao.impl.OrderDaoImpl;
import by.epam.courierexchange.model.dao.impl.TransportDaoImpl;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.model.service.ClientService;
import by.epam.courierexchange.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static final ClientServiceImpl instance = new ClientServiceImpl();
    private static final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
    private static final AddressDaoImpl addressDao = AddressDaoImpl.getInstance();
    private static final int DEFAULT_WEIGHT = 6;

    public ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        return instance;
    }


    @Override
    public boolean createProductClient(Long idUser, Long idProduct, Long idAddress) throws ServiceException {
        try {
            return clientDao.createClientProduct(idUser, idProduct, idAddress);
        } catch (DaoException e) {
            logger.error("DaoException to the create product-client: ", e);
            throw new ServiceException("DaoException to the create product-client: ", e);
        }
    }

    @Override
    public int createClient(String id) throws ServiceException {
        if(!UserValidator.numberIsValid(id)){
            return 0;
        }
        try{
            long userId = Long.parseLong(id);
            return clientDao.createById(userId);
        }catch (DaoException e){
            logger.error("DaoException to the create client: ", e);
            throw new ServiceException("DaoException to the create client: ", e);
        }
    }

    @Override
    public Optional<Address>  updateAddress(Client client, String country, String city, String street, String number, String apartment) throws ServiceException {
        if(!UserValidator.nameIsValid(country) || !UserValidator.nameIsValid(city)
                || !UserValidator.nameIsValid(street) || !UserValidator.numberIsValid(number)
                || !UserValidator.numberIsValid(apartment)){
            return Optional.empty();
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
            clientDao.updateAddressToClient(client.getId(), idAddress);
            return Optional.of(address);
        }catch (DaoException e){
            logger.error("DaoException to the update address: ", e);
            throw new ServiceException("DaoException to the update address: ", e);
        }
    }

    @Override
    public boolean deleteClientProduct(String idStr) throws ServiceException {
        if(!UserValidator.numberIsValid(idStr)){
            return false;
        }
        long id = Long.parseLong(idStr);
        ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
        try {
            return clientDao.deleteClientProductById(id);
        } catch (DaoException e) {
            logger.error("DaoException to the delete client-product: ", e);
            throw new ServiceException("DaoException to the  delete client-product: ", e);
        }
    }

    @Override
    public List<ClientProduct> selectClientProductForCourier(long idCourier, long idTransport) throws ServiceException {
        try {
            Transport transport;
            Optional<Transport> transportOptional;
            int weight;
            TransportDaoImpl transportDao = TransportDaoImpl.getInstance();
            transportOptional = transportDao.selectById(idTransport);
            if(transportOptional.isPresent()){
                transport = transportOptional.get();
                weight = transport.getMaxProductWeight();
            }else{
                weight = DEFAULT_WEIGHT;
            }
            return clientDao.selectClientProductForCourier(idCourier, weight);
        } catch (DaoException e) {
            logger.error("DaoException to the select product-client for courier: ", e);
            throw new ServiceException("DaoException to the select product-client for courier: ", e);
        }
    }
}
