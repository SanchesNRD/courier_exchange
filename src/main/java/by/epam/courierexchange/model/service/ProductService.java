package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    long createProduct(String name,String weight,String width,String height,String length,String type) throws ServiceException;
    Optional<Product> findProductByName(String name) throws ServiceException;
    int deleteProduct(String id) throws ServiceException;
    List<Product> selectAll() throws ServiceException;
}
