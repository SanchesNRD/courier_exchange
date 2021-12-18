package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    private ClientServiceImpl clientService;
    private long id;
    private String strId;
    private Client client;
    private Address address;
    private List<ClientProduct> clientProducts;
    private List<ClientProduct> otherClientProducts;
    private Address otherAddress;
    private String country;
    private String city;
    private String street;
    private String number;
    private String apartment;

    @BeforeEach
    void setUp() {
        client = new Client.ClientBuilder()
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
        address = new Address.AddressBuilder()
                .setId(1)
                .setCountry("Country")
                .setCity("City")
                .setStreet("Street")
                .setStreet_number(1)
                .setApartment(1)
                .build();
        otherAddress = new Address.AddressBuilder()
                .setId(1)
                .setCountry("Country")
                .setCity("City")
                .setStreet("Street")
                .setStreet_number(2)
                .setApartment(2)
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

        ClientProduct clientProduct = new ClientProduct();
        clientProduct.setId(1L);
        clientProduct.setClient(client);
        clientProduct.setProduct(product);
        clientProduct.setAddress(address);

        clientProducts = new ArrayList<>();
        clientProducts.add(clientProduct);
        otherClientProducts = new ArrayList<>();

        country = "Country";
        city = "City";
        street = "Street";
        number = "1";
        apartment = "1";
        id = 1;
        strId = "1";
    }

    @Test
    void createProductClientTrueTest() throws ServiceException {
        when(clientService.createProductClient(id, id, id)).thenReturn(1);
        int result = clientService.createProductClient(id, id, id);
        assertEquals(1, result);
    }

    @Test
    void createProductClientFalseTest() throws ServiceException {
        when(clientService.createProductClient(id, id, id)).thenReturn(1);
        int result = clientService.createProductClient(id, id, id);
        assertNotEquals(2, result);
    }

    @Test
    void createClientTrueTest() throws ServiceException {
        when(clientService.createClient(strId)).thenReturn(1);
        int result = clientService.createClient(strId);
        assertEquals(1, result);
    }

    @Test
    void createClientFalseTest() throws ServiceException {
        when(clientService.createClient(strId)).thenReturn(1);
        int result = clientService.createClient(strId);
        assertNotEquals(2, result);
    }

    @Test
    void updateAddressTrueTest() throws ServiceException {
        when(clientService.updateAddress(client, country, city, street, number, apartment))
                .thenReturn(Optional.of(address));
        Optional<Address> actualAddress = clientService.updateAddress(client, country, city, street, number, apartment);
        assertEquals(address, actualAddress.get());
    }

    @Test
    void updateAddressFalseTest() throws ServiceException {
        when(clientService.updateAddress(client, country, city, street, number, apartment))
                .thenReturn(Optional.of(address));
        Optional<Address> actualAddress = clientService.updateAddress(client, country, city, street, number, apartment);
        assertNotEquals(otherAddress, actualAddress.get());
    }

    @Test
    void deleteClientProductTrueTest() throws ServiceException {
        when(clientService.deleteClientProduct(strId)).thenReturn(1);
        int result = clientService.deleteClientProduct(strId);
        assertEquals(1, result);
    }

    @Test
    void deleteClientProductFalseTest() throws ServiceException {
        when(clientService.deleteClientProduct(strId)).thenReturn(1);
        int result = clientService.deleteClientProduct(strId);
        assertNotEquals(2, result);
    }

    @Test
    void selectClientProductForCourierTrueTest() throws ServiceException {
        when(clientService.selectClientProductForCourier(id, id)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientService.selectClientProductForCourier(id, id);
        assertEquals(clientProducts, actualClientProducts);
    }

    @Test
    void selectClientProductForCourierFalseTest() throws ServiceException {
        when(clientService.selectClientProductForCourier(id, id)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientService.selectClientProductForCourier(id, id);
        assertNotEquals(otherClientProducts, actualClientProducts);
    }
}
