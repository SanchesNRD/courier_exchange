package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.connection.ConnectionPool;
import by.epam.courierexchange.model.dao.ProductDao;
import by.epam.courierexchange.model.entity.ClientProduct;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.entity.ProductType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.courierexchange.model.dao.ColumnName.*;

public class ProductDaoImpl implements ProductDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    private static final String SQL_SELECT_ALL="""
            SELECT id, name, weight, length, width, height, type_id 
            FROM products
            """;
    private static final String SQL_SELECT_BY_ID="""
            SELECT id, name, weight, length, width, height, type_id 
            FROM products WHERE id=?
            """;
    private static final String SQL_SELECT_BY_NAME="""
            SELECT id, name, weight, length, width, height, type_id 
            FROM products WHERE name=?
            """;
    private static final String SQL_DELETE_BY_ID=
            "DELETE FROM products WHERE id=?";
    private static final String SQL_INSERT="""
            INSERT INTO products(id, name, weight, length, width, height, type_id)
            VALUES (?,?,?,?,?,?,?)
            """;
    private static final String SQL_UPDATE="""
            UPDATE products SET weight=?, length=?, width=?, 
            height=?, type_id=? WHERE id=?
            """;
    private static  final String SQL_SELECT_USED_PRODUCT_BY_ID= """
            SELECT client_product.id
            FROM client_product
                RIGHT JOIN orders o on client_product.id = o.client_product_id
            WHERE product_id=?
            """;


    private ProductDaoImpl(){}

    public static ProductDaoImpl getInstance(){
        return instance;
    }

    @Override
    public List<Product> selectAll() throws DaoException {
        List<Product> products = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL))
        {
            while(resultSet.next()){
                Product product = new Product.ProductBuilder()
                        .setId(resultSet.getLong(ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setWeight(resultSet.getInt(PRODUCT_WEIGHT))
                        .setLength(resultSet.getInt(PRODUCT_LENGTH))
                        .setWidth(resultSet.getInt(PRODUCT_WIDTH))
                        .setHeight(resultSet.getInt(PRODUCT_HEIGHT))
                        .setProductType(ProductType.parseType(resultSet.getShort(TYPE_ID)))
                        .build();
                products.add(product);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method selectAllProducts", e);
            throw new DaoException("SQL exception in method selectAllProducts", e);
        }
        return products;
    }

    @Override
    public Optional<Product> selectById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID))
        {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            } else{
                Product product = new Product.ProductBuilder()
                        .setId(resultSet.getLong(ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setWeight(resultSet.getInt(PRODUCT_WEIGHT))
                        .setLength(resultSet.getInt(PRODUCT_LENGTH))
                        .setWidth(resultSet.getInt(PRODUCT_WIDTH))
                        .setHeight(resultSet.getInt(PRODUCT_HEIGHT))
                        .setProductType(ProductType.parseType(resultSet.getShort(TYPE_ID)))
                        .build();
                return Optional.of(product);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method selectProductId", e);
            throw new DaoException("SQL exception in method selectProductId", e);
        }
    }

    public Optional<Product> selectByName(String name) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_NAME))
        {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()){
                return Optional.empty();
            } else{
                Product product = new Product.ProductBuilder()
                        .setId(resultSet.getLong(ID))
                        .setName(resultSet.getString(PRODUCT_NAME))
                        .setWeight(resultSet.getInt(PRODUCT_WEIGHT))
                        .setLength(resultSet.getInt(PRODUCT_LENGTH))
                        .setWidth(resultSet.getInt(PRODUCT_WIDTH))
                        .setHeight(resultSet.getInt(PRODUCT_HEIGHT))
                        .setProductType(ProductType.parseType(resultSet.getShort(TYPE_ID)))
                        .build();
                return Optional.of(product);
            }
        } catch (SQLException e){
            logger.error("SQL exception in method selectProductName", e);
            throw new DaoException("SQL exception in method selectProductName", e);
        }
    }


    @Override
    public boolean deleteById(Long id) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID))
        {
            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e){
            logger.error("SQL exception in method deleteProductById", e);
            throw new DaoException("SQL exception in method deleteProductById", e);
        }
    }

    @Override
    public int create(Product product) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_INSERT))
        {
            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getWeight());
            statement.setInt(4, product.getLength());
            statement.setInt(5, product.getWidth());
            statement.setInt(6, product.getHeight());
            statement.setInt(7, product.getProductType().getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method createProducts", e);
            throw new DaoException("SQL exception in method createProduct", e);
        }
    }

    @Override
    public int update(Product product) throws DaoException {
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_UPDATE))
        {
            statement.setInt(1, product.getWeight());
            statement.setInt(2, product.getLength());
            statement.setInt(3, product.getWidth());
            statement.setInt(4, product.getHeight());
            statement.setInt(5, product.getProductType().getId());
            statement.setLong(6, product.getId());
            return statement.executeUpdate();
        } catch (SQLException e){
            logger.error("SQL exception in method updateProduct ", e);
            throw new DaoException("SQL exception in method updateProduct ", e);
        }
    }

    @Override
    public List<Long> selectUsedProductById(long id) throws DaoException {
        List<Long> products = new ArrayList<>();
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USED_PRODUCT_BY_ID))
        {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                products.add(resultSet.getLong(CLIENT_PRODUCT_ID));
            }
        } catch (SQLException e){
            logger.error("SQL exception in method selectUsedProductByType", e);
            throw new DaoException("SQL exception in method selectUsedProductByType", e);
        }
        return products;
    }
}
