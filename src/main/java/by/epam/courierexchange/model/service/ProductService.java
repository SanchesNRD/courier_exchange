package by.epam.courierexchange.model.service;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;

import java.util.List;

public interface ProductService {
    long createProduct(String name,String weight,String width,String height,String length,String type) throws ServiceException;
    int deleteProduct(String id) throws ServiceException;
    List<Product> selectAll() throws ServiceException;
}
