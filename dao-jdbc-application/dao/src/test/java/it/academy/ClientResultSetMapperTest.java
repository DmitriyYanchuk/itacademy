package it.academy;

import it.academy.dao.jdbc.mapper.ResultSetMapperException;
import it.academy.dao.jdbc.mapper.impl.ClientResultSetMapper;
import it.academy.entity.client.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientResultSetMapperTest {
    @Mock
    private ResultSet rs;

    @Test
    void testMap_happyPath() throws SQLException, ResultSetMapperException {
        //given
        final var expectedClient = new Client(1, "fn", "ln");
        doReturn(Boolean.TRUE).when(rs).next();
        doReturn(expectedClient.getId()).when(rs).getInt("id");
        doReturn(expectedClient.getFirstName()).when(rs).getString("first_name");
        doReturn(expectedClient.getLastName()).when(rs).getString("last_name");

        //when
        final var actualClient = new ClientResultSetMapper().map(rs);

        //then
        assertNotNull(actualClient);
        assertEquals(expectedClient.getId(), actualClient.getId());
        assertEquals(expectedClient.getFirstName(), actualClient.getFirstName());
        assertEquals(expectedClient.getLastName(), actualClient.getLastName());

        verify(rs).next();
        verify(rs).getInt("id");
        verify(rs).getString("first_name");
        verify(rs).getString("last_name");

        verifyNoMoreInteractions(rs);
    }

    @Test
    void testMap_ResultSetMapperException() throws SQLException {
        //given
        final Client expectedClient = new Client(5, "Justyna", "Gorska");
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenReturn(expectedClient.getId());
        when(rs.getString("first_name")).thenThrow(SQLException.class);

        //then
        assertThrows(ResultSetMapperException.class, () -> {
            final Client actualClient = new ClientResultSetMapper().map(rs);
        });
    }
}
