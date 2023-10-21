package it.academy.dao.datasource.factory;

public class DataSourcePropertiesFactoryException extends Exception {

    public DataSourcePropertiesFactoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DataSourcePropertiesFactoryException(final String message) {
        super(message);
    }
}
