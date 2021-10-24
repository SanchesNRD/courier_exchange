package by.epam.courierexchange.model.dao;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends BaseDao<Long, Product>{
    List<Product> selectByType(Integer type) throws DaoException;
    Optional<Product> selectByName(String name) throws DaoException;

    List<Long> selectUsedProductById(long id) throws DaoException;

}
