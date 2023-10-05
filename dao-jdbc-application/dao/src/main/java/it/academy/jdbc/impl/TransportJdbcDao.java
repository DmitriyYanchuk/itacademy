package it.academy.jdbc.impl;

import it.academy.dao.DaoException;
import it.academy.datasource.JdbcDataSourceProperties;
import it.academy.jdbc.GenericJdbcDao;
import it.academy.mapper.ResultSetMapper;
import it.academy.entity.transport.impl.Transport;

import java.sql.*;

public class TransportJdbcDao extends GenericJdbcDao<Transport> {

    public TransportJdbcDao(
            final JdbcDataSourceProperties properties,
            final ResultSetMapper<Transport> mapper
    ) throws DaoException {
        super(properties, "transport", mapper);
    }

    @Override
    protected PreparedStatement getForCreate(final Connection cn, final Transport transport) throws SQLException {
        final PreparedStatement ps = cn.prepareStatement("insert into transport ('model_type_id', 'transport_type_id', 'client_id') values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, transport.getModel().getId());
        ps.setInt(2, transport.getTransportType().getId());

        if (transport.getClient() != null) {
            ps.setInt(3, transport.getClient().getId());
        }

        return ps;
    }
}
