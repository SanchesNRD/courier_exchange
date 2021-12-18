package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.entity.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTest {
    @Mock
    private ProductDaoImpl productDao;
    private Product product;
    private Product otherProduct;
    private List<Product> products;
    private List<Product> otherProducts;
    private List<Long> productsId;
    private List<Long> otherProductsId;

    @BeforeEach
    void setUp() {
        product = new Product.ProductBuilder()
                .setId(1)
                .setName("Name")
                .setWeight(10)
                .setHeight(10)
                .setWidth(10)
                .setLength(10)
                .setProductType(ProductType.DEFAULT)
                .build();
        otherProduct = new Product.ProductBuilder()
                .setId(2)
                .setName("OtherName")
                .setWeight(20)
                .setHeight(20)
                .setWidth(20)
                .setLength(20)
                .setProductType(ProductType.DEFAULT)
                .build();
        products = new ArrayList<>();
        products.add(product);
        products.add(otherProduct);
        otherProducts = new ArrayList<>();

        productsId = new ArrayList<>();
        productsId.add(product.getId());
        productsId.add(otherProduct.getId());
        otherProductsId = new ArrayList<>();
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(productDao.selectAll()).thenReturn(products);
        List<Product> actualProducts = productDao.selectAll();
        assertEquals(products, actualProducts);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(productDao.selectAll()).thenReturn(products);
        List<Product> actualProducts = productDao.selectAll();
        assertNotEquals(otherProducts, actualProducts);
    }
    
    @Test
    void selectAllNotNullTest() throws DaoException {
        when(productDao.selectAll()).thenReturn(products);
        List<Product> actualProducts = productDao.selectAll();
        assertNotNull(actualProducts);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(productDao.selectById(1L)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.selectById(1L);
        assertEquals(product, actualProduct.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(productDao.selectById(1L)).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.selectById(1L);
        assertNotEquals(otherProduct, actualProduct.get());
    }

    @Test
    void selectByNameTrueTest() throws DaoException {
        when(productDao.selectByName("name")).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.selectByName("name");
        assertEquals(product, actualProduct.get());
    }

    @Test
    void selectByNameFalseTest() throws DaoException {
        when(productDao.selectByName("name")).thenReturn(Optional.of(product));
        Optional<Product> actualProduct = productDao.selectByName("name");
        assertNotEquals(otherProduct, actualProduct.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(productDao.deleteById(1L)).thenReturn(true);
        boolean result = productDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(productDao.deleteById(1L)).thenReturn(true);
        boolean result = productDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(productDao.create(product)).thenReturn(1);
        int result = productDao.create(product);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(productDao.create(product)).thenReturn(1);
        int result = productDao.create(product);
        assertNotEquals(2, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(productDao.update(product)).thenReturn(1);
        int result = productDao.update(product);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(productDao.update(product)).thenReturn(1);
        int result = productDao.update(product);
        assertNotEquals(2, result);
    }

    @Test
    void selectUsedProductByIdTrueTest() throws DaoException {
        when(productDao.selectUsedProductById(1L)).thenReturn(productsId);
        List<Long> actualProductsId = productDao.selectUsedProductById(1L);
        assertEquals(productsId, actualProductsId);
    }

    @Test
    void selectUsedProductByIdFalseTest() throws DaoException {
        when(productDao.selectUsedProductById(1L)).thenReturn(productsId);
        List<Long> actualProductsId = productDao.selectUsedProductById(1L);
        assertNotEquals(otherProductsId, actualProductsId);
    }
}
