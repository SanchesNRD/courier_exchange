package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.TransportDaoImpl;
import by.epam.courierexchange.model.entity.Transport;
import by.epam.courierexchange.model.service.TransportService;
import by.epam.courierexchange.model.validator.CourierExchangeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransportServiceImpl implements TransportService {
    private static final Logger logger = LogManager.getLogger();
    private static final TransportServiceImpl instance = new TransportServiceImpl();
    private static final TransportDaoImpl transportDao = TransportDaoImpl.getInstance();

    public TransportServiceImpl() {
    }

    public static TransportServiceImpl getInstance() {
        return instance;
    }

    @Override
    public int deleteTransport(String idStr) throws ServiceException {
        if(CourierExchangeValidator.numberIsInvalid(idStr)){
            return 0;
        }
        long id = Long.parseLong(idStr);
        try {
            List<Long> usedProduct = transportDao.selectUsedProductById(id);
            if(!usedProduct.isEmpty()){
                return -1;
            }
            transportDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("DaoException to the delete or select used transport: ", e);
            throw new ServiceException("DaoException to the delete or select used transport: ", e);
        }
        return 1;
    }

    @Override
    public List<Transport> selectAll() throws ServiceException {
        try {
            return transportDao.selectAll();
        } catch (DaoException e) {
            logger.error("DaoException to the select all transports: ", e);
            throw new ServiceException("DaoException to the select all transports: ", e);
        }
    }
}
