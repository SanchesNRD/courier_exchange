package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.*;
import by.epam.courierexchange.util.PasswordEncryption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourierServiceImplTest {
    @Mock
    private CourierServiceImpl courierService;
    private String strId;
    private long id;
    private Courier courier;
    private Transport transport;
    private Transport otherTransport;
    private String name;
    private String speed;
    private String weight;
    private String type;

    @BeforeEach
    void setUp() {
        strId = "1";
        id = 1;
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
        transport = new Transport.TransportBuilder()
                .setId(1)
                .setName("Name")
                .setAverageSpeed(20)
                .setMaxProductWeight(10)
                .setTransportType(TransportType.BIKE)
                .build();
        otherTransport = new Transport.TransportBuilder()
                .setId(2)
                .setName("OtherName")
                .setAverageSpeed(30)
                .setMaxProductWeight(10)
                .setTransportType(TransportType.BIKE)
                .build();

        name = "Name";
        speed = "20";
        weight = "10";
        type = "1";

    }

    @Test
    void createOrderTrueTest() throws ServiceException {
        when(courierService.createOrder(strId,id)).thenReturn(1);
        int result = courierService.createOrder(strId,id);
        assertEquals(1, result);
    }

    @Test
    void createOrderFalseTest() throws ServiceException {
        when(courierService.createOrder(strId,id)).thenReturn(1);
        int result = courierService.createOrder(strId,id);
        assertNotEquals(2, result);
    }

    @Test
    void updateTransportTrueTest() throws ServiceException {
        when(courierService.updateTransport(courier, name, speed, weight, type)).thenReturn(Optional.of(transport));
        Optional<Transport> actualTransport = courierService.updateTransport(courier, name, speed, weight, type);
        assertEquals(transport, actualTransport.get());
    }

    @Test
    void updateTransportFalseTest() throws ServiceException {
        when(courierService.updateTransport(courier, name, speed, weight, type)).thenReturn(Optional.of(transport));
        Optional<Transport> actualTransport = courierService.updateTransport(courier, name, speed, weight, type);
        assertNotEquals(otherTransport, actualTransport.get());
    }
}
