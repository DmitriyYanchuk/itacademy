package it.academy.dao.jdbc.mapper.impl;

import it.academy.dao.jdbc.mapper.ResultSetMapper;
import it.academy.dao.jdbc.mapper.ResultSetMapperException;
import it.academy.entity.client.Client;
import it.academy.entity.base.interfaces.Identifier;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransportResultSetMapper implements ResultSetMapper<Transport> {

    @Override
    public Transport map(final ResultSet resultSet) throws ResultSetMapperException {
        try {
            if (!resultSet.next()) {
                return null;
            }

            final Integer id = resultSet.getInt("id");

            final Integer modelTypeId = resultSet.getInt("model_type_id");
            final Model model = mapToEnum(Model.class, modelTypeId);

            final Integer transportTypeId = resultSet.getInt("transport_type_id");
            final TransportType transportType = mapToEnum(TransportType.class, transportTypeId);

            final Integer clientId = resultSet.getInt("client_id");
            final Client client = new Client(clientId, null, null);

            return new Transport(id, model, transportType, client);
        } catch (final SQLException exc) {
            throw new ResultSetMapperException("Failed to parse", exc);
        }
    }

    private static <T extends Enum<T> & Identifier> T mapToEnum(
            final Class<T> enumClass,
            final Integer id
    ) {
        for (final T value : enumClass.getEnumConstants()) {
            if (value.getId().equals(id)) {
                return value;
            }
        }

        return null;
    }
}
