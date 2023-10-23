package it.academy;

import it.academy.dao.jdbc.mapper.ResultSetMapperException;
import it.academy.dao.jdbc.mapper.impl.TransportResultSetMapper;
import it.academy.entity.client.Client;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransportResultSetMapperTest {

    @Mock
    private ResultSet rs;

    @Test
    void testMap_happyPath() throws SQLException, ResultSetMapperException {
        //given
        final Client expectedClient = new Client(5, "Justyna", "Gorska");
        final Model expectedModel = Model.PORSCHE_PANAMERA;
        final TransportType expectedType = TransportType.AUTOMOBILE;
        final Transport expectedTransport = new Transport(5, expectedModel, expectedType, expectedClient);

        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenReturn(expectedTransport.getId());
        when(rs.getInt("model_type_id")).thenReturn(expectedTransport.getModel().getId());
        when(rs.getInt("transport_type_id")).thenReturn(expectedTransport.getTransportType().getId());
        when(rs.getInt("client_id")).thenReturn(expectedTransport.getClient().getId());

        //when
        final Transport actualTransport = new TransportResultSetMapper().map(rs);

        //then
        assertNotNull(actualTransport, "actualTransport is null");
        assertEquals(expectedTransport.getId(), actualTransport.getId());
        assertEquals(expectedType.getId(), actualTransport.getTransportType().getId());
        assertEquals(expectedModel.getId(), actualTransport.getModel().getId());
        assertEquals(expectedClient.getId(), actualTransport.getClient().getId());

        verify(rs).next();
        verify(rs).getInt("id");
        verify(rs).getInt("model_type_id");
        verify(rs).getInt("transport_type_id");
        verify(rs).getInt("client_id");
        verifyNoMoreInteractions(rs);
    }

    @Test
    void testMap_ResultSetMapperException() throws SQLException {
        //given
        final Transport expectedTransport = new Transport(5, null, null, null);
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenThrow(SQLException.class);

        //then
        assertThrows(ResultSetMapperException.class, () -> {
            final Transport actualTransport = new TransportResultSetMapper().map(rs);
        });
    }
}
