package it.academy.dao.jdbc.impl;

import it.academy.dao.DaoException;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.GenericJdbcDao;
import it.academy.dao.jdbc.JdbcDaoException.ClientJdbcDaoException;
import it.academy.dao.jdbc.mapper.ResultSetMapper;
import it.academy.entity.client.Client;

import java.sql.*;

public class ClientJdbcDao extends GenericJdbcDao<Client, ClientJdbcDaoException> {

    public ClientJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Client> mapper
    ) throws DaoException {
        super(properties, "client", ClientJdbcDaoException::new, mapper);
    }

    @Override
    protected PreparedStatement getPrepareStatementForCreate(final Connection cn, final Client client) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into client (first_name, last_name) values (?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setString(1, client.getFirstName());
        ps.setString(2, client.getLastName());

        return ps;
    }
}
