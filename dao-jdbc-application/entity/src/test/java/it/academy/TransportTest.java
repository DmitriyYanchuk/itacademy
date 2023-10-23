package it.academy;

import it.academy.entity.client.Client;
import it.academy.entity.transport.Model;
import it.academy.entity.transport.TransportType;
import it.academy.entity.transport.impl.Transport;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransportTest {

    final Client actualClient = new Client(6, "Eugene", "Aliyeva");
    final Model actualModel = Model.PORSCHE_PANAMERA;
    final TransportType actualTransportType = TransportType.AUTOMOBILE;
    final Transport actualTransport = new Transport(6, actualModel, actualTransportType, actualClient);

    @Test
    void testGetTransportClient_happyPath() {
        final Client expectedClient = new Client(6, "Eugene", "Aliyeva");
        final Client actualClient = actualTransport.getClient();

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void testGetTransportModel_happyPath() {
        final Model expectedModel = Model.PORSCHE_PANAMERA;
        final Model actualModel = actualTransport.getModel();

        assertEquals(expectedModel, actualModel);
    }

    @Test
    void testGetTransportType_happyPath() {
        final TransportType expectedType = TransportType.AUTOMOBILE;
        final TransportType actualType = actualTransport.getTransportType();

        assertEquals(expectedType, actualType);
    }

    @Test
    void testGetTransportId_happyPath() {
        final int expectedId = 6;
        final int actualId = actualTransport.getId();

        assertEquals(expectedId, actualId);
    }
}
