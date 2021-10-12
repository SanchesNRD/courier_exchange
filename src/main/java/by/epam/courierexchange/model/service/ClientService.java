package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.User;

import java.util.Optional;

public interface ClientService {
    boolean createProductClient(Long idUser, Long idProduct, Long idAddress) throws ServiceException;
    Optional<Client> createClient(User user) throws ServiceException;
}
