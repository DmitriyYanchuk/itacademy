package it.academy.datasource;

public interface DataSourcePropertiesFactory {

    JdbcDataSourceProperties create(String source) throws DataSourcePropertiesFactoryException;
}
