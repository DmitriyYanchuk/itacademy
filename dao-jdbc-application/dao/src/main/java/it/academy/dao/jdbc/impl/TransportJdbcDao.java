package it.academy.dao.jdbc.impl;

import it.academy.dao.DaoException;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.GenericJdbcDao;
import it.academy.dao.jdbc.JdbcDaoException.TransportJdbcDaoException;
import it.academy.dao.jdbc.mapper.ResultSetMapper;
import it.academy.entity.transport.impl.Transport;

import java.sql.*;

public class TransportJdbcDao extends GenericJdbcDao<Transport, TransportJdbcDaoException> {

    public TransportJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Transport> mapper
    ) throws DaoException {
        super(properties, "transport", TransportJdbcDaoException::new, mapper);
    }

    @Override
    protected PreparedStatement getPrepareStatementForCreate(final Connection cn, final Transport transport) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into transport (model_type_id, transport_type_id, client_id) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, transport.getModel().getId());
        ps.setInt(2, transport.getTransportType().getId());

        if (transport.getClient() != null) {
            ps.setInt(3, transport.getClient().getId());
        } else {
            ps.setNull(3, Types.INTEGER);
        }

        return ps;
    }
}
