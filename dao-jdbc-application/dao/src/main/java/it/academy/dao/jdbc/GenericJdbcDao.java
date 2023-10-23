package it.academy.dao.jdbc;

import it.academy.dao.Dao;
import it.academy.dao.DaoException;
import it.academy.dao.datasource.impl.JdbcDataSourceProperties;
import it.academy.dao.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.function.BiFunction;

public abstract class GenericJdbcDao<T, E extends DaoException> implements Dao<T> {

    private final JdbcDataSourceProperties properties;

    private final String tableName;

    private final ResultSetMapper<T> mapper;

    private final BiFunction<String, Exception, E> exceptionCreator;

    public GenericJdbcDao(
            final JdbcDataSourceProperties properties,
            final String tableName,
            final BiFunction<String, Exception, E> exceptionCreator,
            final ResultSetMapper<T> mapper
    ) throws DaoException {
        this.properties = properties;
        this.tableName = tableName;
        this.exceptionCreator = exceptionCreator;
        this.mapper = mapper;

        registerDriver(properties.getDriver());
    }

    protected abstract PreparedStatement getPrepareStatementForCreate(Connection cn,T entity) throws SQLException;

    @Override
    public final Integer create(final T entity) throws E {
        try (final Connection connection = createConnection()) {
            final PreparedStatement ps = getPrepareStatementForCreate(connection, entity);
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

            throw exceptionCreator.apply("Failed to retrieve id", null);
        } catch (final Exception exc) {
            throw exceptionCreator.apply("Failed to create: " + tableName, exc);
        }
    }

    @Override
    public final T read(final Integer id) throws E {
        try (final Connection connection = createConnection()) {
            final String readSql = "select * from %s where id = ?".formatted(tableName);
            final PreparedStatement ps = connection.prepareStatement(readSql);
            ps.setInt(id,1);

            final ResultSet rs = ps.executeQuery();

            return mapper.map(rs);
        } catch (final Exception exc) {
            throw exceptionCreator.apply("Failed to read: " + tableName, exc);
        }
    }

    @Override
    public final Integer update(final T entity) throws DaoException {
        return null;
    }

    @Override
    public final Integer delete(final T entity) throws DaoException {
        return null;
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getUrl(),
                properties.getUser(),
                properties.getPassword()
        );
    }

    private static void registerDriver(final String driverName) throws DaoException {
        try {
            final Class<?> driverClass = Class.forName(driverName);

            final boolean isNotRegistered = DriverManager.drivers().noneMatch(dr -> dr.getClass().equals(driverClass));
            if (isNotRegistered) {
                final Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driver);
            }
        } catch (final Exception exc) {
            throw new DaoException("Failed to register driver: " + driverName, exc);
        }
    }
}
