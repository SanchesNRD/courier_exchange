package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.util.PasswordEncryption;
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
public class CourierDaoImplTest {
    @Mock
    private CourierDaoImpl courierDao;
    private Courier courier;
    private Courier otherCourier;
    private List<Courier> couriers;
    private List<Courier> otherCouriers;

    @BeforeEach
    void setUp() {
        courier = new Courier.CourierBuilder()
                .setBuilder(new User.UserBuilder()
                        .setId(1)
                        .setLogin("Login")
                        .setName("Name")
                        .setSurname("Surname")
                        .setPassword(PasswordEncryption.encode("a12345678"))
                        .setMail("mail@gmail.com")
                        .setPhone("+375291234567")
                        .setImage("avatar.png")
                        .setUserStatus(UserStatus.COURIER_CONFIRMED))
                .setTransport(1)
                .build();
        otherCourier = new Courier.CourierBuilder()
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
        couriers = new ArrayList<>();
        couriers.add(courier);
        couriers.add(otherCourier);
        otherCouriers = new ArrayList<>();
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(courierDao.selectAll()).thenReturn(couriers);
        List<Courier> actualCouriers = courierDao.selectAll();
        assertEquals(couriers, actualCouriers);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(courierDao.selectAll()).thenReturn(couriers);
        List<Courier> actualCouriers = courierDao.selectAll();
        assertNotEquals(otherCouriers, actualCouriers);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(courierDao.selectAll()).thenReturn(couriers);
        List<Courier> actualCouriers = courierDao.selectAll();
        assertNotNull(actualCouriers);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(courierDao.selectById(1L)).thenReturn(Optional.of(courier));
        Optional<Courier> actualCourier = courierDao.selectById(1L);
        assertEquals(courier, actualCourier.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(courierDao.selectById(1L)).thenReturn(Optional.of(courier));
        Optional<Courier> actualCourier = courierDao.selectById(1L);
        assertNotEquals(otherCourier, actualCourier.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(courierDao.deleteById(1L)).thenReturn(true);
        boolean result = courierDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(courierDao.deleteById(1L)).thenReturn(true);
        boolean result = courierDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(courierDao.create(courier)).thenReturn(1);
        int result = courierDao.create(courier);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(courierDao.create(courier)).thenReturn(1);
        int result = courierDao.create(courier);
        assertNotEquals(2, result);
    }

    @Test
    void createByIdTrueTest() throws DaoException {
        when(courierDao.createById(1)).thenReturn(true);
        boolean result = courierDao.createById(1);
        assertTrue(result);
    }

    @Test
    void createByIdFalseTest() throws DaoException {
        when(courierDao.createById(1)).thenReturn(true);
        boolean result = courierDao.createById(1);
        assertNotEquals(false, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(courierDao.update(courier)).thenReturn(1);
        int result = courierDao.update(courier);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(courierDao.update(courier)).thenReturn(1);
        int result = courierDao.update(courier);
        assertNotEquals(2, result);
    }

    @Test
    void updateTransportToCourierTrueTest() throws DaoException {
        when(courierDao.updateTransportToCourier(1,1)).thenReturn(1);
        int result = courierDao.updateTransportToCourier(1,1);
        assertEquals(1,result);
    }

    @Test
    void updateTransportToCourierFalseTest() throws DaoException {
        when(courierDao.updateTransportToCourier(1,1)).thenReturn(1);
        int result = courierDao.updateTransportToCourier(1,1);
        assertNotEquals(2,result);
    }
}
