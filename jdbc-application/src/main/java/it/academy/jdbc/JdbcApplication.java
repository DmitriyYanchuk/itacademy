package it.academy.jdbc;

import it.academy.client.Client;
import it.academy.transport.Model;
import it.academy.transport.TransportType;
import it.academy.transport.impl.Transport;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcApplication {

    private static final Properties PROPERTIES = takeProperties();

    public static void main(final String[] args) throws JdbcException {
        try (final Connection connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES)) {
            final String transportQuery = "select tr.id, mt.\"name\" as modelName, tt.\"name\" as transportType, cl.first_name as firstName, cl.last_name as lastName  from transport tr \n" +
                    "\tleft join model_type mt on tr.model_type_id = mt.id\n" +
                    "\tleft join transport_type tt on tr.transport_type_id = tt.id \n" +
                    "\tleft join client cl on tr.client_id = cl.id";

            final Statement statement = connection.createStatement();
            final ResultSet transportRS = statement.executeQuery(transportQuery);

            final List<Transport> transports = new ArrayList<>();
            while (transportRS.next()) {
                final int id = transportRS.getInt("id");
                final String modelName = transportRS.getString("modelName");
                final Model model = new Model(modelName);

                final String type = transportRS.getString("transportType");
                final TransportType transportType = TransportType.valueOf(type.toUpperCase());

                final String firstName = transportRS.getString("firstName");
                final String lastName = transportRS.getString("lastName");
                final Client client = new Client(firstName, lastName);

                final Transport transport = new Transport(model, transportType, client);
                transports.add(transport);
            }

            System.out.println(transports);
        } catch (SQLException e) {
            throw new JdbcException("SQL connection error", e);
        }
    }

    private static Properties takeProperties() {
        try (final InputStream inputStream = JdbcApplication.class.getClassLoader().getResourceAsStream("application.properties")) {
            final Properties properties = new Properties();
            properties.load(inputStream);

            return properties;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
