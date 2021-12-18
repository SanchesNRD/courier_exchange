package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceImplTest {
    @Mock
    private AddressServiceImpl addressService;
    private String country;
    private String city;
    private String street;
    private String number;
    private String apartment;

    @BeforeEach
    void setUp() {
        country = "Country";
        city = "City";
        street = "Street";
        number = "1";
        apartment = "1";
    }

    @Test
    void createAddressTrueTest() throws ServiceException {
        when(addressService.createAddress(country, city, street, number, apartment))
                .thenReturn(1L);
        long result = addressService.createAddress(country, city, street, number, apartment);
        assertEquals(1L, result);
    }

    @Test
    void createAddressFalseTest() throws ServiceException {
        when(addressService.createAddress(country, city, street, number, apartment))
                .thenReturn(1L);
        long result = addressService.createAddress(country, city, street, number, apartment);
        assertNotEquals(2L, result);
    }
}
