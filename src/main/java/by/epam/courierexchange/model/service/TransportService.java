package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Transport;

import java.util.List;

public interface TransportService {
    int deleteTransport(String id) throws ServiceException;
    List<Transport> selectAll() throws ServiceException;
}
