package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.util.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderDaoImplTest {
    @Mock
    private OrderDaoImpl orderDao;
    private Order order;
    private Order otherOrder;
    private List<Order> orders;
    private List<Order> otherOrders;
    private List<Long> ordersId;
    private List<Long> otherOrdersId;

    @BeforeEach
    void setUp() {
        Client client = new Client.ClientBuilder()
                .setBuilder(new User.UserBuilder()
                        .setId(1)
                        .setLogin("Login")
                        .setName("Name")
                        .setSurname("Surname")
                        .setPassword(PasswordEncryption.encode("a12345678"))
                        .setMail("mail@gmail.com")
                        .setPhone("+375291234567")
                        .setImage("avatar.png")
                        .setUserStatus(UserStatus.CONFIRMED))
                .setAddress(1)
                .build();
        Client otherClient = new Client.ClientBuilder()
                .setBuilder(new User.UserBuilder()
                        .setId(3)
                        .setLogin("MyLogin")
                        .setName("MyName")
                        .setSurname("MySurname")
                        .setPassword(PasswordEncryption.encode("a12345678"))
                        .setMail("my_mail@gmail.com")
                        .setPhone("+375291234568")
                        .setImage("avatar.png")
                        .setUserStatus(UserStatus.CONFIRMED))
                .setAddress(1)
                .build();
        Product product = new Product.ProductBuilder()
                .setId(1)
                .setName("Name")
                .setWeight(10)
                .setHeight(10)
                .setWidth(10)
                .setLength(10)
                .setProductType(ProductType.DEFAULT)
                .build();
        Address address = new Address.AddressBuilder()
                .setId(1)
                .setCountry("Country")
                .setCity("City")
                .setStreet("Street")
                .setStreet_number(1)
                .setApartment(1)
                .build();
        ClientProduct clientProduct = new ClientProduct();
        clientProduct.setId(1);
        clientProduct.setClient(client);
        clientProduct.setProduct(product);
        clientProduct.setAddress(address);

        ClientProduct otherClientProduct = new ClientProduct();
        otherClientProduct.setId(2);
        otherClientProduct.setClient(otherClient);
        otherClientProduct.setProduct(product);
        otherClientProduct.setAddress(address);

        Courier courier = new Courier.CourierBuilder()
                .setBuilder(new User.UserBuilder()
                        .setId(2)
                        .setLogin("OtherLogin")
                        .setName("OtherName")
                        .setSurname("OtherSurname")
                        .setPassword(PasswordEncryption.encode("a12345678"))
                        .setMail("OtherMail@gmail.com")
                        .setPhone("+375291234567")
                        .setImage("avatar.png")
                        .setUserStatus(UserStatus.COURIER_CONFIRMED))
                .setTransport(1)
                .build();

        order = new Order.OrderBuilder()
                .setId(1)
                .setClientProduct(clientProduct)
                .setCourier(courier)
                .setDate(Date.valueOf("2021-10-10"))
                .setOrderStatus(OrderStatus.DELIVERED)
                .build();
        otherOrder = new Order.OrderBuilder()
                .setId(2)
                .setClientProduct(otherClientProduct)
                .setCourier(courier)
                .setDate(Date.valueOf("2021-10-10"))
                .setOrderStatus(OrderStatus.DELIVERED)
                .build();
        orders = new ArrayList<>();
        orders.add(order);
        orders.add(otherOrder);
        otherOrders = new ArrayList<>();

        ordersId = new ArrayList<>();
        ordersId.add(order.getId());
        ordersId.add(otherOrder.getId());
        otherOrdersId = new ArrayList<>();
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(orderDao.selectAll()).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectAll();
        assertEquals(orders, actualOrders);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(orderDao.selectAll()).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectAll();
        assertNotEquals(otherOrders, actualOrders);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(orderDao.selectAll()).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectAll();
        assertNotNull(actualOrders);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(orderDao.selectById(1L)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.selectById(1L);
        assertEquals(order, actualOrder.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(orderDao.selectById(1L)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.selectById(1L);
        assertNotEquals(otherOrder, actualOrder.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(orderDao.deleteById(1L)).thenReturn(true);
        boolean result = orderDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(orderDao.deleteById(1L)).thenReturn(true);
        boolean result = orderDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(orderDao.create(order)).thenReturn(1);
        int result = orderDao.create(order);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(orderDao.create(order)).thenReturn(1);
        int result = orderDao.create(order);
        assertNotEquals(2, result);
    }

    @Test
    void createByFieldTrueTest() throws DaoException {
        when(orderDao.createByField(1L, 1L, "2021-10-10", OrderStatus.AGREED)).thenReturn(1);
        int result = orderDao.createByField(1L, 1L, "2021-10-10", OrderStatus.AGREED);
        assertEquals(1, result);
    }

    @Test
    void createByFieldFalseTest() throws DaoException {
        when(orderDao.createByField(1L, 1L, "2021-10-10", OrderStatus.AGREED)).thenReturn(1);
        int result = orderDao.createByField(1L, 1L, "2021-10-10", OrderStatus.AGREED);
        assertNotEquals(2, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(orderDao.update(order)).thenReturn(1);
        int result =  orderDao.update(order);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(orderDao.update(order)).thenReturn(1);
        int result =  orderDao.update(order);
        assertNotEquals(2, result);
    }

    @Test
    void selectIdByCourierTrueTest() throws DaoException {
        when(orderDao.selectIdByCourier(1L, OrderStatus.DELIVERED)).thenReturn(1L);
        long result = orderDao.selectIdByCourier(1L, OrderStatus.DELIVERED);
        assertEquals(1L, result);
    }

    @Test
    void selectIdByCourierFalseTest() throws DaoException {
        when(orderDao.selectIdByCourier(1L, OrderStatus.DELIVERED)).thenReturn(1L);
        long result = orderDao.selectIdByCourier(1L, OrderStatus.DELIVERED);
        assertNotEquals(2L, result);
    }

    @Test
    void selectActiveOrderByCourierTrueTest() throws DaoException {
        when(orderDao.selectActiveOrderByCourier(1L, OrderStatus.DELIVERED)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.selectActiveOrderByCourier(1L, OrderStatus.DELIVERED);
        assertEquals(order, actualOrder.get());
    }

    @Test
    void selectActiveOrderByCourierFalseTest() throws DaoException {
        when(orderDao.selectActiveOrderByCourier(1L, OrderStatus.DELIVERED)).thenReturn(Optional.of(order));
        Optional<Order> actualOrder = orderDao.selectActiveOrderByCourier(1L, OrderStatus.DELIVERED);
        assertNotEquals(otherOrder, actualOrder.get());
    }

    @Test
    void updateStatusTrueTest() throws DaoException {
        when(orderDao.updateStatus(1L, OrderStatus.COMPLETED)).thenReturn(1);
        int result = orderDao.updateStatus(1L, OrderStatus.COMPLETED);
        assertEquals(1, result);
    }

    @Test
    void updateStatusFalseTest() throws DaoException {
        when(orderDao.updateStatus(1L, OrderStatus.COMPLETED)).thenReturn(1);
        int result = orderDao.updateStatus(1L, OrderStatus.COMPLETED);
        assertNotEquals(2, result);
    }

    @Test
    void selectHistoryByCourierTrueTest() throws DaoException {
        when(orderDao.selectHistoryByCourier(1L, OrderStatus.DELIVERED)).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectHistoryByCourier(1L, OrderStatus.DELIVERED);
        assertEquals(orders, actualOrders);
    }

    @Test
    void selectHistoryByCourierFalseTest() throws DaoException {
        when(orderDao.selectHistoryByCourier(1L, OrderStatus.DELIVERED)).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectHistoryByCourier(1L, OrderStatus.DELIVERED);
        assertNotEquals(otherOrders, actualOrders);
    }

    @Test
    void selectHistoryByClientTrueTest() throws DaoException {
        when(orderDao.selectHistoryByClient(1L, OrderStatus.DELIVERED)).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectHistoryByClient(1L, OrderStatus.DELIVERED);
        assertEquals(orders, actualOrders);
    }

    @Test
    void selectHistoryByClientFalseTest() throws DaoException {
        when(orderDao.selectHistoryByClient(1L, OrderStatus.DELIVERED)).thenReturn(orders);
        List<Order> actualOrders = orderDao.selectHistoryByClient(1L, OrderStatus.DELIVERED);
        assertNotEquals(otherOrders, actualOrders);
    }

    @Test
    void selectOrderByUserTrueTest() throws DaoException {
        when(orderDao.selectOrderByUser(1L)).thenReturn(ordersId);
        List<Long> actualOrdersId = orderDao.selectOrderByUser(1L);
        assertEquals(ordersId, actualOrdersId);
    }

    @Test
    void selectOrderByUserFalseTest() throws DaoException {
        when(orderDao.selectOrderByUser(1L)).thenReturn(ordersId);
        List<Long> actualOrdersId = orderDao.selectOrderByUser(1L);
        assertNotEquals(otherOrdersId, actualOrdersId);
    }
}
