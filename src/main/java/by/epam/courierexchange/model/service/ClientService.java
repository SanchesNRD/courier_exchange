package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Address;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.ClientProduct;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    int createProductClient(Long idUser, Long idProduct, Long idAddress) throws ServiceException;
    int createClient(String id) throws ServiceException;
    Optional<Address>  updateAddress (Client client, String country, String city, String street, String number, String apartment)
        throws ServiceException;
    int deleteClientProduct (String id) throws ServiceException;
    List<ClientProduct> selectClientProductForCourier(long idCourier, long idTransport) throws ServiceException;
}
