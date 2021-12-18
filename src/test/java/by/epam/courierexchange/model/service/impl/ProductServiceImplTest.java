package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Product;
import by.epam.courierexchange.model.entity.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductServiceImpl productService;
    private String strId;
    private String name;
    private String weight;
    private String width;
    private String height;
    private String length;
    private String type;
    private long id;
    private long otherId;
    private List<Product> products;
    private List<Product> otherProducts;

    @BeforeEach
    void setUp() {
        name = "Name";
        weight = "10";
        width = "10";
        height = "10";
        length = "10";
        type = "1";
        strId = "1";
        id = 1;
        otherId = 2;
        Product product = new Product.ProductBuilder()
                .setId(1)
                .setName("Name")
                .setWeight(10)
                .setHeight(10)
                .setWidth(10)
                .setLength(10)
                .setProductType(ProductType.DEFAULT)
                .build();
        Product otherProduct = new Product.ProductBuilder()
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
    }

    @Test
    void createProductTrueTest() throws ServiceException {
        when(productService.createProduct(name, weight, width, height, length, type)).thenReturn(id);
        long result = productService.createProduct(name, weight, width, height, length, type);
        assertEquals(id, result);
    }

    @Test
    void createProductFalseTest() throws ServiceException {
        when(productService.createProduct(name, weight, width, height, length, type)).thenReturn(id);
        long result = productService.createProduct(name, weight, width, height, length, type);
        assertNotEquals(otherId, result);
    }

    @Test
    void deleteProductTrueTest() throws ServiceException {
        when(productService.deleteProduct(strId)).thenReturn(1);
        int result = productService.deleteProduct(strId);
        assertEquals(1, result);
    }

    @Test
    void deleteProductFalseTest() throws ServiceException {
        when(productService.deleteProduct(strId)).thenReturn(1);
        int result = productService.deleteProduct(strId);
        assertNotEquals(2, result);
    }

    @Test
    void selectAllTrueTest() throws ServiceException {
        when(productService.selectAll()).thenReturn(products);
        List<Product> actualProducts = productService.selectAll();
        assertEquals(products, actualProducts);
    }

    @Test
    void selectAllFalseTest() throws ServiceException {
        when(productService.selectAll()).thenReturn(products);
        List<Product> actualProducts = productService.selectAll();
        assertNotEquals(otherProducts, actualProducts);
    }
}
