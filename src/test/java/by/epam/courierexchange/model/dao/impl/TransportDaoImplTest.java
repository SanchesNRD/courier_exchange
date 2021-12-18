package by.epam.courierexchange.model.dao.impl;

import by.epam.courierexchange.exception.DaoException;
import by.epam.courierexchange.model.entity.Transport;
import by.epam.courierexchange.model.entity.TransportType;
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
public class TransportDaoImplTest {
    @Mock
    private TransportDaoImpl transportDao;
    private Transport transport;
    private Transport otherTransport;
    private List<Transport> transports;
    private List<Transport> otherTransports;
    private List<Long> transportsId;
    private List<Long> otherTransportsId;

    @BeforeEach
    void setUp() {
        transport = new Transport.TransportBuilder()
                .setId(1)
                .setName("Name")
                .setAverageSpeed(10)
                .setMaxProductWeight(10)
                .setTransportType(TransportType.BIKE)
                .build();
        otherTransport = new Transport.TransportBuilder()
                .setId(2)
                .setName("OtherName")
                .setAverageSpeed(10)
                .setMaxProductWeight(10)
                .setTransportType(TransportType.BIKE)
                .build();
        transports = new ArrayList<>();
        transports.add(transport);
        transports.add(otherTransport);
        otherTransports = new ArrayList<>();

        transportsId = new ArrayList<>();
        transportsId.add(transport.getId());
        transportsId.add(otherTransport.getId());
        otherTransportsId = new ArrayList<>();
    }

    @Test
    void selectAllTrueTest() throws DaoException {
        when(transportDao.selectAll()).thenReturn(transports);
        List<Transport> actualTransports = transportDao.selectAll();
        assertEquals(transports, actualTransports);
    }

    @Test
    void selectAllFalseTest() throws DaoException {
        when(transportDao.selectAll()).thenReturn(transports);
        List<Transport> actualTransports = transportDao.selectAll();
        assertNotEquals(otherTransports, actualTransports);
    }

    @Test
    void selectAllNotNullTest() throws DaoException {
        when(transportDao.selectAll()).thenReturn(transports);
        List<Transport> actualTransports = transportDao.selectAll();
        assertNotNull(actualTransports);
    }

    @Test
    void selectByIdTrueTest() throws DaoException {
        when(transportDao.selectById(1L)).thenReturn(Optional.of(transport));
        Optional<Transport> actualTransport = transportDao.selectById(1L);
        assertEquals(transport, actualTransport.get());
    }

    @Test
    void selectByIdFalseTest() throws DaoException {
        when(transportDao.selectById(1L)).thenReturn(Optional.of(transport));
        Optional<Transport> actualTransport = transportDao.selectById(1L);
        assertNotEquals(otherTransport, actualTransport.get());
    }

    @Test
    void deleteByIdTrueTest() throws DaoException {
        when(transportDao.deleteById(1L)).thenReturn(true);
        boolean result = transportDao.deleteById(1L);
        assertTrue(result);
    }

    @Test
    void deleteByIdFalseTest() throws DaoException {
        when(transportDao.deleteById(1L)).thenReturn(true);
        boolean result = transportDao.deleteById(1L);
        assertNotEquals(false, result);
    }

    @Test
    void createTrueTest() throws DaoException {
        when(transportDao.create(transport)).thenReturn(1);
        int result = transportDao.create(transport);
        assertEquals(1, result);
    }

    @Test
    void createFalseTest() throws DaoException {
        when(transportDao.create(transport)).thenReturn(1);
        int result = transportDao.create(transport);
        assertNotEquals(2, result);
    }

    @Test
    void updateTrueTest() throws DaoException {
        when(transportDao.update(transport)).thenReturn(1);
        int result = transportDao.update(transport);
        assertEquals(1, result);
    }

    @Test
    void updateFalseTest() throws DaoException {
        when(transportDao.update(transport)).thenReturn(1);
        int result = transportDao.update(transport);
        assertNotEquals(2, result);
    }

    @Test
    void selectIdTransportTrueTest() throws DaoException {
        when(transportDao.selectIdTransport("name", 10, 10, (short)1)).thenReturn(1L);
        long result = transportDao.selectIdTransport("name", 10, 10, (short)1);
        assertEquals(1L, result);
    }

    @Test
    void selectIdTransportFalseTest() throws DaoException {
        when(transportDao.selectIdTransport("name", 10, 10, (short)1)).thenReturn(1L);
        long result = transportDao.selectIdTransport("name", 10, 10, (short)1);
        assertNotEquals(2L, result);
    }

    @Test
    void selectUsedProductByIdTrueTest() throws DaoException {
        when(transportDao.selectUsedProductById(1)).thenReturn(transportsId);
        List<Long> actualTransportsId = transportDao.selectUsedProductById(1);
        assertEquals(transportsId, actualTransportsId);
    }

    @Test
    void selectUsedProductByIdFalseTest() throws DaoException {
        when(transportDao.selectUsedProductById(1)).thenReturn(transportsId);
        List<Long> actualTransportsId = transportDao.selectUsedProductById(1);
        assertNotEquals(otherTransportsId, actualTransportsId);
    }
}
