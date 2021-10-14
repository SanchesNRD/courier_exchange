package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.AddressDaoImpl;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.model.service.ClientService;
import by.epam.courierexchange.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static final ClientServiceImpl instance = new ClientServiceImpl();
    private static final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
    private static final AddressDaoImpl addressDao = AddressDaoImpl.getInstance();

    public ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        return instance;
    }


    @Override
    public boolean createProductClient(Long idUser, Long idProduct, Long idAddress) throws ServiceException {
        ClientProduct clientProduct = new ClientProduct();
        try {
            return clientDao.createClientProduct(idUser, idProduct, idAddress);
        } catch (DaoException e) {
            logger.error("DaoException to the create product-client: ", e);
            throw new ServiceException("DaoException to the create product-client: ", e);
        }
    }

    @Override
    public Optional<Client> createClient(User user) throws ServiceException {
        try{
            if(clientDao.createById(user.getId())){
                Client client = new Client.ClientBuilder()
                        .setBuilder(new User.UserBuilder()
                                .setId(user.getId())
                                .setLogin(user.getLogin())
                                .setPassword(user.getPassword())
                                .setMail(user.getMail())
                                .setName(user.getName())
                                .setSurname(user.getSurname())
                                .setPhone(user.getPhone())
                                .setUserStatus(user.getUserStatus()))
                        .build();
                return Optional.of(client);
            }else{
                return Optional.empty();
            }
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
}
