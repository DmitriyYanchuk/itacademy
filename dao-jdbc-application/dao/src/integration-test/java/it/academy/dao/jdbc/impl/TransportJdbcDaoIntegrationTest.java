package it.academy.dao.jdbc.impl;

import it.academy.dao.Dao;
import it.academy.dao.datasource.factory.DataSourcePropertiesFactory;
import it.academy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.mapper.impl.TransportResultSetMapper;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransportJdbcDaoIntegrationTest extends BaseJdbcDaoIntegrationTest {

    @Test
    void testCreate_clientIsNull_happyPath() throws Exception {
        //given
        final Model expectedModel = Model.PORSCHE_PANAMERA;
        final TransportType expectedTransportType = TransportType.AUTOMOBILE;
        final DataSourcePropertiesFactory factory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = factory.create("application-test.properties");
        final TransportResultSetMapper mapper = new TransportResultSetMapper();

        final Dao<Transport> transportDao = new TransportJdbcDao(properties, mapper);
        final Transport transport = new Transport(null, expectedModel, expectedTransportType, null);

        //when
        final Integer id = transportDao.create(transport);

        //then
        final ResultSetVerifier verifier = (rs) -> {
            Integer transportId = rs.getInt("id");
            Integer clientId = rs.getObject("client_id", Integer.class);
            Integer modelTypeId = rs.getInt("model_type_id");
            Integer transportTypeId = rs.getInt("transport_type_id");

            Assertions.assertEquals(id, transportId);
            Assertions.assertNull(clientId);
            Assertions.assertEquals(expectedModel.getId(), modelTypeId);
            Assertions.assertEquals(expectedTransportType.getId(), transportTypeId);
        };

        verifyCreatedRow("transport", id, verifier);
    }
}
