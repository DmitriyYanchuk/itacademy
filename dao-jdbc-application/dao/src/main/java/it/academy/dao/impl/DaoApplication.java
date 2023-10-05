package it.academy.dao.impl;

import it.academy.dao.Dao;
import it.academy.datasource.DataSourcePropertiesFactory;
import it.academy.datasource.JdbcDataSourceProperties;
import it.academy.datasource.impl.FileDataSourcePropertiesFactory;
import it.academy.provider.DaoProvider;
import it.academy.provider.impl.DaoProviderJdbc;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;

public class DaoApplication {

    public static void main(final String[] args) throws Exception {
        final DataSourcePropertiesFactory dataSourcePropertiesFactory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = dataSourcePropertiesFactory.create("application.properties");

        final DaoProvider provider = new DaoProviderJdbc(properties);

        final Dao<Transport> transportDao = provider.provide(Transport.class);

        transportDao.create(new Transport(null, Model.BMW_520D, TransportType.AUTOMOBILE, null));
    }
}