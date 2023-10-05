package it.academy.mapper.impl;

import it.academy.mapper.ResultSetMapper;
import it.academy.mapper.ResultSetMapperException;
import it.academy.entity.client.Client;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientResultSetMapper implements ResultSetMapper<Client> {

    @Override
    public Client map(final ResultSet resultSet) throws ResultSetMapperException {
        try {
            if (!resultSet.next()) {
                return null;
            }

            final Integer id = resultSet.getInt("id");
            final String firstName = resultSet.getString("first_name");
            final String lastName = resultSet.getString("last_name");

            return new Client(id, firstName, lastName);
        } catch (final SQLException exc) {
            throw new ResultSetMapperException("Failed to map client", exc);
        }
    }
}
