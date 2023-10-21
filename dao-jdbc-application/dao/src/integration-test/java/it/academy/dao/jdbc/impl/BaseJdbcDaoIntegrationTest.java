package it.academy.dao.jdbc.impl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

class BaseJdbcDaoIntegrationTest {

    protected interface ResultSetVerifier {

        void verify(ResultSet rs) throws SQLException;

    }

    private static final String DB_URL = "datasource.jdbc.url";
    private static final String DB_USER = "datasource.jdbc.user";
    private static final String DB_PASSWORD = "datasource.jdbc.user";
    private static final String DB_URL_PARAMETERS = "datasource.jdbc.url.parameters";

    private static Properties testProperties;
    private static Connection connection;

    @BeforeAll
    public static void beforeAll() throws IOException, SQLException {
        final var properties = getTestProperties("application-test.properties");

        final var url = properties.getProperty(DB_URL) + properties.getProperty(DB_URL_PARAMETERS);

        connection = DriverManager.getConnection(
                url,
                properties.getProperty(DB_USER),
                properties.getProperty(DB_PASSWORD)
        );
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    protected static void verifyCreatedRow(final String tableName, final Integer id, final ResultSetVerifier verifier) throws SQLException {
        final String selectSql = "select * from " + tableName + " where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(selectSql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            int rowCounter = 0;
            while (rs.next()) {
                rowCounter++;

                if (rowCounter > 1) {
                    continue;
                }

                verifier.verify(rs);
            }

            if (rowCounter != 1) {
                Assertions.fail("Unexpected row number, must be 1 but is: " + rowCounter);
            }
        }
    }

    private static Properties getTestProperties(final String fileName) throws IOException {
        if (testProperties != null) {
            return testProperties;
        }

        final var in = BaseJdbcDaoIntegrationTest.class.getClassLoader().getResourceAsStream(fileName);
        if (in == null) {
            throw new FileNotFoundException("File is not found: " + fileName);
        }

        try (final var reader = new InputStreamReader(in)) {
            final var properties = new Properties();
            properties.load(reader);

            testProperties = properties;
        }

        return testProperties;
    }
}
