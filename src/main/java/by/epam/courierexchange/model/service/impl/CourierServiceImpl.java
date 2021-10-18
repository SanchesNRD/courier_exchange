package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.*;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.model.service.CourierService;
import by.epam.courierexchange.model.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

public class CourierServiceImpl implements CourierService {
    private static final Logger logger = LogManager.getLogger();
    private static final CourierServiceImpl instance = new CourierServiceImpl();
    private static final CourierDaoImpl courierDao = CourierDaoImpl.getInstance();
    private static final TransportDaoImpl  transportDao = TransportDaoImpl.getInstance();
    private static final OrderDaoImpl orderDao = OrderDaoImpl.getInstance();
    private static final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();

    public CourierServiceImpl() {
    }

    public static CourierServiceImpl getInstance() {
        return instance;
    }

    @Override
    public int createOrder(String clientProductStr, long courier) throws ServiceException {
        if (!UserValidator.numberIsValid(clientProductStr)) {
            return 0;
        }
        Optional<ClientProduct> clientProductOptional;
        long order;
        Date date = new Date();
        Object dateSql = new Timestamp(date.getTime());
        long clientProduct = Long.parseLong(clientProductStr);
        int result;
        try {
            clientProductOptional = clientDao.selectClientProductById(clientProduct);
            order = orderDao.selectIdByCourier(courier, OrderStatus.AGREED);
            if (clientProductOptional.get().getClient().getId() != courier && order == 0) {
                result = orderDao.createByField(clientProduct, courier, dateSql, OrderStatus.AGREED);
            } else {
                result = 0;
            }
        } catch (DaoException e) {
            logger.error("DaoException to the update transport: ", e);
            throw new ServiceException("DaoException to the update transport: ", e);
        }
        return result;
    }

    @Override
    public Optional<Transport> updateTransport(Courier courier, String name, String speed, String weight, String type) throws ServiceException {
        if(!UserValidator.nameIsValid(name) || !UserValidator.numberIsValid(speed)
                || !UserValidator.numberIsValid(weight) || !UserValidator.typeIsValid(type)){
            return Optional.empty();
        }
        Transport transport;
        int speedInt = Integer.parseInt(speed);
        int weightInt = Integer.parseInt(weight);
        short typeShort = Short.parseShort(type);
        long idTransport;
        try{
            transport = new Transport.TransportBuilder()
                    .setName(name)
                    .setAverageSpeed(speedInt)
                    .setMaxProductWeight(weightInt)
                    .setTransportType(TransportType.parseType(typeShort))
                    .build();
            idTransport = transportDao.selectIdTransport(name, speedInt, weightInt, typeShort);
            if(idTransport == 0){
                transportDao.create(transport);
                idTransport = transportDao.selectIdTransport(name, speedInt, weightInt, typeShort);
            }
            courierDao.updateTransportToCourier(courier.getId(), idTransport);
            return Optional.of(transport);
        }catch (DaoException e){
            logger.error("DaoException to the update transport: ", e);
            throw new ServiceException("DaoException to the update transport: ", e);
        }


    }

    @Override
    public Optional<Order> selectActiveOrderByCourier(long courier) throws ServiceException {
        try {
            return orderDao.selectActiveOrderByCourier(courier, OrderStatus.AGREED);
        } catch (DaoException e) {
            logger.error("DaoException to the select active order: ", e);
            throw new ServiceException("DaoException to the select active order: ", e);
        }
    }
}
