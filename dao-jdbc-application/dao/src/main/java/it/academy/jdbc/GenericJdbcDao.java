package it.academy.jdbc;

import it.academy.dao.Dao;
import it.academy.dao.DaoException;
import it.academy.datasource.JdbcDataSourceProperties;
import it.academy.mapper.ResultSetMapper;

import java.sql.*;

public abstract class GenericJdbcDao<T> implements Dao<T> {

    private final JdbcDataSourceProperties properties;
    private final String tableName;

    private final ResultSetMapper<T> mapper;

    public GenericJdbcDao(
            final JdbcDataSourceProperties properties,
            final String tableName,
            final ResultSetMapper<T> mapper) throws DaoException {
        this.properties = properties;
        this.tableName = tableName;
        this.mapper = mapper;

        registerDriver(properties.getDriver());
    }

    protected abstract PreparedStatement getForCreate(Connection cn,T entity) throws SQLException;

    @Override
    public final Integer create(final T entity) throws DaoException {
        try (final Connection connection = createConnection()) {
            final PreparedStatement ps = getForCreate(connection, entity);
            return ps.executeUpdate();
        } catch (final Exception exc) {
            throw new DaoException("Failed to create: " + tableName, exc);
        }
    }

    @Override
    public final T read(final Integer id) throws DaoException {
        try (final Connection connection = createConnection()) {
            final String readSql = "select * from %s where id = ?".formatted(tableName);
            final PreparedStatement ps = connection.prepareStatement(readSql);
            ps.setInt(id,1);

            final ResultSet rs = ps.executeQuery();

            return mapper.map(rs);
        } catch (final Exception exc) {
            throw new DaoException("Failed to read: " + tableName, exc);
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
            final boolean isNotRegistered = DriverManager.drivers().noneMatch(dr -> dr.getClass().equals(driverName));
            if (isNotRegistered) {
                final Driver driver = (Driver) driverClass.getDeclaredConstructor().newInstance();
                DriverManager.registerDriver(driver);
            }

        } catch (final Exception exc) {
            throw new DaoException("Failed to register driver: " + driverName, exc);
        }
    }
}
