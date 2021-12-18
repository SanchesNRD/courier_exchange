package by.epam.courierexchange.model.service.impl;

import by.epam.courierexchange.exception.ServiceException;
import by.epam.courierexchange.model.entity.Transport;
import by.epam.courierexchange.model.entity.TransportType;
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
public class TransportServiceImplTest {
    @Mock
    private TransportServiceImpl transportService;
    private String strId;
    private List<Transport> transports;
    private List<Transport> otherTransports;

    @BeforeEach
    void setUp() {
        strId = "1";
        Transport transport = new Transport.TransportBuilder()
                .setId(1)
                .setName("Name")
                .setAverageSpeed(10)
                .setMaxProductWeight(10)
                .setTransportType(TransportType.BIKE)
                .build();
        Transport otherTransport = new Transport.TransportBuilder()
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
    }

    @Test
    void deleteTransportTrueTest() throws ServiceException {
        when(transportService.deleteTransport(strId)).thenReturn(1);
        int result = transportService.deleteTransport(strId);
        assertEquals(1, result);
    }

    @Test
    void deleteTransportFalseTest() throws ServiceException {
        when(transportService.deleteTransport(strId)).thenReturn(1);
        int result = transportService.deleteTransport(strId);
        assertNotEquals(2, result);
    }

    @Test
    void selectAllTrueTest() throws ServiceException {
        when(transportService.selectAll()).thenReturn(transports);
        List<Transport> actualTransports  = transportService.selectAll();
        assertEquals(transports, actualTransports);
    }

    @Test
    void selectAllFalseTest() throws ServiceException {
        when(transportService.selectAll()).thenReturn(transports);
        List<Transport> actualTransports  = transportService.selectAll();
        assertNotEquals(otherTransports, actualTransports);
    }
}
