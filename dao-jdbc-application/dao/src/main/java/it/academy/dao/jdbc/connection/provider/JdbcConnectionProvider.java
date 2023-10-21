package it.academy.dao.jdbc.connection.provider;

import java.sql.Connection;

public interface JdbcConnectionProvider {

    Connection getConnection();

}
