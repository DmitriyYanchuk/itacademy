package it.academy;

import it.academy.dao.Dao;
import it.academy.dao.DaoException;
import it.academy.dao.datasource.factory.DataSourcePropertiesFactory;
import it.academy.dao.datasource.factory.DataSourcePropertiesFactoryException;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.datasource.factory.impl.FileDataSourcePropertiesFactory;
import it.academy.dao.provider.DaoProvider;
import it.academy.dao.jdbc.provider.DaoProviderJdbc;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;

public class DaoApplication {

    public static void main(final String[] args) throws DataSourcePropertiesFactoryException, DaoException {
        final DataSourcePropertiesFactory dataSourcePropertiesFactory = new FileDataSourcePropertiesFactory();
        final JdbcDataSourceProperties properties = dataSourcePropertiesFactory.create("application.properties");

        final DaoProvider provider = new DaoProviderJdbc(properties);

        final Dao<Transport> transportDao = provider.provide(Transport.class);

        final Transport transport = new Transport(null, Model.BMW_520D, TransportType.AUTOMOBILE, null);
        final Integer id = transportDao.create(transport);

        System.out.println("ID = " + id);
    }
}