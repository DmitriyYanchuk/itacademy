package it.academy.provider.impl;

import it.academy.dao.Dao;
import it.academy.dao.DaoException;
import it.academy.datasource.JdbcDataSourceProperties;
import it.academy.jdbc.impl.ClientJdbcDao;
import it.academy.jdbc.impl.TransportJdbcDao;
import it.academy.mapper.impl.ClientResultSetMapper;
import it.academy.mapper.impl.TransportResultSetMapper;
import it.academy.provider.DaoProvider;
import it.academy.entity.client.Client;
import it.academy.entity.base.BaseEntity;
import it.academy.entity.transport.impl.Transport;

import java.util.HashMap;
import java.util.Map;

public class DaoProviderJdbc implements DaoProvider {

    private final Map<Class<? extends BaseEntity>, Dao<? extends BaseEntity>> map;
    private final JdbcDataSourceProperties properties;

    public DaoProviderJdbc(final JdbcDataSourceProperties properties) throws DaoException {
        this.properties = properties;
        this.map = new HashMap<>();

        map.put(Transport.class, new TransportJdbcDao(properties, new TransportResultSetMapper()));
        map.put(Client.class, new ClientJdbcDao(properties, new ClientResultSetMapper()));

    }

    @Override
    public <E extends BaseEntity, D extends Dao<E>> D provide(Class<E> entityClass) {
        return (D) map.get(entityClass);
    }
}
