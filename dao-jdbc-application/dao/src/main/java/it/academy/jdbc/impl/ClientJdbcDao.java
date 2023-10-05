package it.academy.jdbc.impl;

import it.academy.dao.DaoException;
import it.academy.datasource.JdbcDataSourceProperties;
import it.academy.jdbc.GenericJdbcDao;
import it.academy.mapper.ResultSetMapper;
import it.academy.entity.client.Client;

import java.sql.*;

public class ClientJdbcDao extends GenericJdbcDao<Client> {

    public ClientJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Client> mapper
    ) throws DaoException {
        super(properties, "client", mapper);
    }

    @Override
    protected PreparedStatement getForCreate(final Connection cn, final Client client) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into client ('first_name', 'last_name') values (?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, client.getFirstName());
        ps.setString(2, client.getLastName());

        return ps;
    }
}
