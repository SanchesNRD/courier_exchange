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
public class ClientDaoImplTest {
    @Mock
    private ClientDaoImpl clientDao;
    private Client client;
    private Client otherClient;
    private List<Client> clients;
    private List<Client> otherClients;
    private ClientProduct clientProduct;
    private ClientProduct otherClientProduct;
    private List<ClientProduct> clientProducts;
    private List<ClientProduct> otherClientProducts;

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
        otherClient = new Client.ClientBuilder()
                .setBuilder(new User.UserBuilder()
                        .setId(2)
                        .setLogin("OtherLogin")
                        .setName("OtherName")
                        .setSurname("OtherSurname")
                        .setPassword(PasswordEncryption.encode("a12345678"))
                        .setMail("OtherMail@gmail.com")
                        .setPhone("+375291234567")
                        .setImage("avatar.png")
                        .setUserStatus(UserStatus.CONFIRMED))
                .setAddress(2L)
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

        clients = new ArrayList<>();
        clients.add(client);
        clients.add(otherClient);
        otherClients = new ArrayList<>();

        clientProduct = new ClientProduct();
        clientProduct.setId(1L);
        clientProduct.setClient(client);
        clientProduct.setProduct(product);
        clientProduct.setAddress(address);

        otherClientProduct = new ClientProduct();
        otherClientProduct.setId(2L);
        otherClientProduct.setClient(otherClient);
        otherClientProduct.setProduct(product);
        otherClientProduct.setAddress(address);

        clientProducts = new ArrayList<>();
        clientProducts.add(clientProduct);
        clientProducts.add(otherClientProduct);
        otherClientProducts = new ArrayList<>();
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(clientDao.selectAll()).thenReturn(clients);
        List<Client> actualClients = clientDao.selectAll();
        assertEquals(clients, actualClients);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(clientDao.selectAll()).thenReturn(clients);
        List<Client> actualClients = clientDao.selectAll();
        assertNotEquals(otherClients, actualClients);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(clientDao.selectAll()).thenReturn(clients);
        List<Client> actualClients = clientDao.selectAll();
        assertNotNull(actualClients);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(clientDao.selectById(1L)).thenReturn(Optional.of(client));
        Optional<Client> actualClient = clientDao.selectById(1L);
        assertEquals(client, actualClient.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(clientDao.selectById(1L)).thenReturn(Optional.of(client));
        Optional<Client> actualClient = clientDao.selectById(1L);
        assertNotEquals(otherClient, actualClient.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(clientDao.deleteById(1L)).thenReturn(true);
        boolean result = clientDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(clientDao.deleteById(1L)).thenReturn(true);
        boolean result = clientDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(clientDao.create(client)).thenReturn(1);
        int result = clientDao.create(client);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(clientDao.create(client)).thenReturn(1);
        int result = clientDao.create(client);
        assertNotEquals(2, result);
    }

    @Test
    void createByIdTrueTest() throws DaoException {
        when(clientDao.createById(1L)).thenReturn(1);
        int result = clientDao.createById(1L);
        assertEquals(1, result);
    }

    @Test
    void createByIdFalseTest() throws DaoException {
        when(clientDao.createById(1L)).thenReturn(1);
        int result = clientDao.createById(1L);
        assertNotEquals(2, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(clientDao.update(client)).thenReturn(1);
        int result = clientDao.update(client);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(clientDao.update(client)).thenReturn(1);
        int result = clientDao.update(client);
        assertNotEquals(2, result);
    }

    @Test
    void updateAddressToClientTrueTest() throws DaoException {
        when(clientDao.updateAddressToClient(1,1)).thenReturn(1);
        int result = clientDao.updateAddressToClient(1,1);
        assertEquals(1,result);
    }

    @Test
    void updateAddressToClientFalseTest() throws DaoException {
        when(clientDao.updateAddressToClient(1,1)).thenReturn(1);
        int result = clientDao.updateAddressToClient(1,1);
        assertNotEquals(2,result);
    }

    @Test
    void selectAllClientProductTrueTest() throws DaoException {
        when(clientDao.selectAllClientProduct()).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientDao.selectAllClientProduct();
        assertEquals(clientProducts, actualClientProducts);
    }

    @Test
    void selectAllClientProductFalseTest() throws DaoException {
        when(clientDao.selectAllClientProduct()).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientDao.selectAllClientProduct();
        assertNotEquals(otherClientProducts, actualClientProducts);
    }

    @Test
    void selectAllClientProductNotNullTest() throws DaoException {
        when(clientDao.selectAllClientProduct()).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientDao.selectAllClientProduct();
        assertNotNull(actualClientProducts);
    }

    @Test
    void selectClientProductForCourierTrueTest() throws DaoException {
        when(clientDao.selectClientProductForCourier(1,10)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProduct = clientDao.selectClientProductForCourier(1,10);
        assertEquals(clientProducts, actualClientProduct);
    }

    @Test
    void selectClientProductForCourierFalseTest() throws DaoException {
        when(clientDao.selectClientProductForCourier(1,10)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProduct = clientDao.selectClientProductForCourier(1,10);
        assertNotEquals(otherClientProducts, actualClientProduct);
    }

    @Test
    void selectClientProductByIdTrueTest() throws DaoException {
        when(clientDao.selectClientProductById(1)).thenReturn(Optional.of(clientProduct));
        Optional<ClientProduct> actualClientProduct = clientDao.selectClientProductById(1);
        assertEquals(clientProduct, actualClientProduct.get());
    }

    @Test
    void selectClientProductByIdFalseTest() throws DaoException {
        when(clientDao.selectClientProductById(1)).thenReturn(Optional.of(clientProduct));
        Optional<ClientProduct> actualClientProduct = clientDao.selectClientProductById(1);
        assertNotEquals(otherClientProduct, actualClientProduct.get());
    }

    @Test
    void deleteClientProductByIdTrueTest() throws DaoException {
        when(clientDao.deleteClientProductById(1L)).thenReturn(1);
        int result = clientDao.deleteClientProductById(1L);
        assertEquals(1, result);
    }

    @Test
    void deleteClientProductByIdFalseTest() throws DaoException {
        when(clientDao.deleteClientProductById(1L)).thenReturn(1);
        int result = clientDao.deleteClientProductById(1L);
        assertNotEquals(2, result);
    }

    @Test
    void createClientProductTrueTest() throws DaoException {
        when(clientDao.createClientProduct(1L,1L,1L)).thenReturn(1);
        int result = clientDao.createClientProduct(1L, 1L, 1L);
        assertEquals(1, result);
    }

    @Test
    void createClientProductFalseTest() throws DaoException {
        when(clientDao.createClientProduct(1L,1L,1L)).thenReturn(1);
        int result = clientDao.createClientProduct(1L, 1L, 1L);
        assertNotEquals(2, result);
    }

    @Test
    void selectActiveClientProductByIdTrueTest() throws DaoException{
        when(clientDao.selectActiveClientProductById(1)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientDao.selectActiveClientProductById(1);
        assertEquals(clientProducts, actualClientProducts);
    }

    @Test
    void selectActiveClientProductByIdFalseTest() throws DaoException{
        when(clientDao.selectActiveClientProductById(1)).thenReturn(clientProducts);
        List<ClientProduct> actualClientProducts = clientDao.selectActiveClientProductById(1);
        assertNotEquals(otherClientProducts, actualClientProducts);
    }

    @Test
    void deleteClientProductByClientTrueTest() throws DaoException {
        when(clientDao.deleteClientProductByClient(1L)).thenReturn(true);
        boolean result = clientDao.deleteClientProductByClient(1L);
        assertTrue(result);
    }

    @Test
    void deleteClientProductByClientFalseTest() throws DaoException {
        when(clientDao.deleteClientProductByClient(1L)).thenReturn(true);
        boolean result = clientDao.deleteClientProductByClient(1L);
        assertNotEquals(false, result);
    }
}
