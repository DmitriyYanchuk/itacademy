package it.academy.dao.datasource.factory;

import it.academy.dao.datasource.impl.JdbcDataSourceProperties;

public interface DataSourcePropertiesFactory {

    JdbcDataSourceProperties create(String source) throws DataSourcePropertiesFactoryException;
}
