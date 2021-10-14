package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Client;
import by.epam.courierexchange.model.entity.Courier;
import by.epam.courierexchange.model.entity.Transport;

import java.util.Optional;

public interface CourierService {
    Optional<Transport> updateTransport (Courier courier, String name, String speed, String weight, String type)
            throws ServiceException;
}
