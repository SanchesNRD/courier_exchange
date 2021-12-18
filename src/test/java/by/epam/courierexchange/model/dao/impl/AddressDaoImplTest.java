package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Address;
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
public class AddressDaoImplTest {
    @Mock
    private AddressDaoImpl addressDao;
    private Address address;
    private Address otherAddress;
    private List<Address> addresses;
    private List<Address> otherAddresses;

    @BeforeEach
    void setUp() {
        address = new Address.AddressBuilder()
                .setId(1)
                .setCountry("Беларусь")
                .setCity("Минск")
                .setStreet("Матусевича")
                .setStreet_number(12)
                .setApartment(89)
                .build();
        otherAddress = new Address.AddressBuilder()
                .setId(2)
                .setCountry("Беларусь")
                .setCity("Гродно")
                .setStreet("Пушкинская")
                .setStreet_number(2)
                .setApartment(12)
                .build();
        addresses = new ArrayList<>();
        addresses.add(address);
        addresses.add(otherAddress);
        otherAddresses = new ArrayList<>();
    }

    @Test
    void createTrueTest() throws DaoException {
        when(addressDao.create(address)).thenReturn(1);
        int result = addressDao.create(address);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(addressDao.create(address)).thenReturn(1);
        int result = addressDao.create(address);
        assertNotEquals(2, result);
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(addressDao.selectAll()).thenReturn(addresses);
        List<Address> actualAddresses = addressDao.selectAll();
        assertEquals(addresses, actualAddresses);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(addressDao.selectAll()).thenReturn(addresses);
        List<Address> actualAddresses = addressDao.selectAll();
        assertNotEquals(otherAddresses, actualAddresses);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(addressDao.selectAll()).thenReturn(addresses);
        List<Address> actualAddresses = addressDao.selectAll();
        assertNotNull(actualAddresses);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(addressDao.selectById(1L)).thenReturn(Optional.of(address));
        Optional<Address> actualAddress = addressDao.selectById(1L);
        assertEquals(address, actualAddress.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(addressDao.selectById(1L)).thenReturn(Optional.of(address));
        Optional<Address> actualAddress = addressDao.selectById(1L);
        assertNotEquals(otherAddress, actualAddress.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(addressDao.deleteById(1L)).thenReturn(true);
        boolean result = addressDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(addressDao.deleteById(1L)).thenReturn(true);
        boolean result = addressDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void updateTrueTest() throws DaoException{
        when(addressDao.update(address)).thenReturn(1);
        int result = addressDao.update(address);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(addressDao.update(address)).thenReturn(1);
        int result = addressDao.update(address);
        assertNotEquals(2, result);
    }

    @Test
    void selectIdAddressTrueTest() throws DaoException {
        when(addressDao.selectIdAddress("Беларусь", "Минск", "Матусевича", 12, 89))
                .thenReturn(1L);
        long actualId = addressDao.selectIdAddress("Беларусь", "Минск", "Матусевича", 12, 89);
        assertEquals(1L, actualId);
    }

    @Test
    void selectIdAddressFalseTest() throws DaoException {
        when(addressDao.selectIdAddress("Беларусь", "Минск", "Матусевича", 12, 89))
                .thenReturn(1L);
        long actualId = addressDao.selectIdAddress("Беларусь", "Минск", "Матусевича", 12, 89);
        assertNotEquals(2L, actualId);
    }
}
