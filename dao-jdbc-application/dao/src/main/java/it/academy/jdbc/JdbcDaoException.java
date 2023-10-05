package it.academy.jdbc;

import it.academy.dao.DaoException;

public class JdbcDaoException extends DaoException {
    public JdbcDaoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public static class ClientJdbcDaoException extends JdbcDaoException {

        public ClientJdbcDaoException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }

    public static class TransportJdbcDaoException extends JdbcDaoException {

        public TransportJdbcDaoException(final String message, final Throwable cause) {
            super(message, cause);
        }
    }
}
