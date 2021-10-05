package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ClientDaoImpl;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private static final ClientServiceImpl instance = new ClientServiceImpl();
    private static final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();

    public ClientServiceImpl() {
    }

    public static ClientServiceImpl getInstance() {
        return instance;
    }


    @Override
    public boolean createProductClient(Long idUser, Long idProduct) throws ServiceException {
        ClientProduct clientProduct = new ClientProduct();
        clientProduct.setClient(idUser);
        clientProduct.setProduct(idProduct);
        try {
            return clientDao.createClientProduct(clientProduct);
        } catch (DaoException e) {
            logger.error("DaoException to the create product-client: ", e);
            throw new ServiceException("DaoException to the create product-client: ", e);
        }
    }
}
