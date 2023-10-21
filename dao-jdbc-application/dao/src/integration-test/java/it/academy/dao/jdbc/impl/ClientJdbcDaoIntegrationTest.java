package it.academy.dao.jdbc.impl;

import it.academy.dao.Dao;
import it.academy.dao.datasource.factory.DataSourcePropertiesFactory;
import it.academy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.mapper.impl.ClientResultSetMapper;
import it.academy.entity.client.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientJdbcDaoIntegrationTest extends BaseJdbcDaoIntegrationTest {

    @Test
    void testCreate_clientIsNull_happyPath() throws Exception {
        //given
        final DataSourcePropertiesFactory factory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = factory.create("application-test.properties");
        final ClientResultSetMapper mapper = new ClientResultSetMapper();

        final Dao<Client> clientDao = new ClientJdbcDao(properties, mapper);
        final Client client = new Client(null, "fn", "ln");

        //when
        final Integer id = clientDao.create(client);

        //then
        final ResultSetVerifier verifier = (rs) -> {
            Integer transportId = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");


            Assertions.assertEquals(id, transportId);
            Assertions.assertEquals(client.getFirstName(), firstName);
            Assertions.assertEquals(client.getLastName(), lastName);
        };

        verifyCreatedRow("client", id, verifier);
    }
}
