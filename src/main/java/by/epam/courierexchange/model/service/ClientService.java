package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;

public interface ClientService {
    boolean createProductClient(Long idUser, Long idProduct) throws ServiceException;
    boolean createClient(Long idUser) throws ServiceException;
}
