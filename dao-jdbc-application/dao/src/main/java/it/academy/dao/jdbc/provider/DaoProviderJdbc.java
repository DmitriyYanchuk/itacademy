package it.academy.dao.jdbc.provider;

import it.academy.dao.Dao;
import it.academy.dao.DaoException;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.impl.ClientJdbcDao;
import it.academy.dao.jdbc.mapper.impl.ClientResultSetMapper;
import it.academy.dao.provider.DaoProvider;
import it.academy.dao.jdbc.impl.TransportJdbcDao;
import it.academy.dao.jdbc.mapper.impl.TransportResultSetMapper;
import it.academy.entity.client.Client;
import it.academy.entity.base.BaseEntity;
import it.academy.entity.transport.impl.Transport;

import java.util.HashMap;
import java.util.Map;

public class DaoProviderJdbc implements DaoProvider {

    private final Map<Class<? extends BaseEntity>, Dao<? extends BaseEntity>> map;

    public DaoProviderJdbc(final JdbcDataSourceProperties properties) throws DaoException {
        this.map = new HashMap<>();

        map.put(Transport.class, new TransportJdbcDao(properties, new TransportResultSetMapper()));
        map.put(Client.class, new ClientJdbcDao(properties, new ClientResultSetMapper()));

    }

    @Override
    public <E extends BaseEntity, D extends Dao<E>> D provide(final Class<E> entityClass) {
        return (D) map.get(entityClass);
    }
}
