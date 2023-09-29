package it.academy.jdbc;

public class JdbcException extends Exception{

    public JdbcException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
