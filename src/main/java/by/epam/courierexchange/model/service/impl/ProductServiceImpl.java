package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.dao.impl.ProductDaoImpl;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.entity.ProductType;
import by.epam.courierexchange.model.service.ProductService;
import by.epam.courierexchange.model.validator.CourierExchangeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger();
    private static final ProductServiceImpl instance = new ProductServiceImpl();
    private static final ProductDaoImpl productDao = ProductDaoImpl.getInstance();

    public ProductServiceImpl() {
    }

    public static ProductServiceImpl getInstance() {
        return instance;
    }


    @Override
    public long createProduct(String name, String weight, String width, String height, String length, String type) throws ServiceException {
        if(CourierExchangeValidator.numberIsInvalid(weight) || CourierExchangeValidator.numberIsInvalid(width)
                || CourierExchangeValidator.numberIsInvalid(height) || CourierExchangeValidator.numberIsInvalid(length)
                || CourierExchangeValidator.typeIsInvalid(type) || CourierExchangeValidator.nameIsInvalid(name)){
            return 0;
        }
        Optional<Product> optionalProduct;
        Product product = new Product.ProductBuilder()
                .setName(name)
                .setWeight(Integer.parseInt(weight))
                .setWidth(Integer.parseInt(weight))
                .setHeight(Integer.parseInt(height))
                .setLength(Integer.parseInt(length))
                .setProductType(ProductType.parseType(Short.parseShort(type)))
                .build();
        try{
            optionalProduct = productDao.selectByName(name);
            if(optionalProduct.isEmpty()){
                productDao.create(product);
                optionalProduct = productDao.selectByName(name);
            }
            return optionalProduct.get().getId();
        } catch (DaoException e){
            logger.error("DaoException to the create product: ", e);
            throw new ServiceException("DaoException to the create product: ", e);
        }
    }

    @Override
    public int deleteProduct(String idStr) throws ServiceException {
        if(CourierExchangeValidator.numberIsInvalid(idStr)){
            return 0;
        }
        long id = Long.parseLong(idStr);
        try {
            List<Long> usedProduct = productDao.selectUsedProductById(id);
            if(!usedProduct.isEmpty()){
                return -1;
            }
            productDao.deleteById(id);
        } catch (DaoException e) {
            logger.error("DaoException to the delete or select used product: ", e);
            throw new ServiceException("DaoException to the delete or select used product: ", e);
        }
        return 1;
    }

    @Override
    public List<Product> selectAll() throws ServiceException {
        try {
            return productDao.selectAll();
        } catch (DaoException e) {
            logger.error("DaoException to the select all products: ", e);
            throw new ServiceException("DaoException to the select all products: ", e);
        }
    }
}
