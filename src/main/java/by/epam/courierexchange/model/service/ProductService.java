package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;

import java.util.Optional;

public interface ProductService {
    boolean createProduct(String name,String weight,String width,String height,String length,String type) throws ServiceException;
    Optional<Product> findProductByName(String name) throws ServiceException;
}
