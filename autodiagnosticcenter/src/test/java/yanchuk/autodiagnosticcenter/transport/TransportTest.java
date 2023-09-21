package yanchuk.autodiagnosticcenter.transport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransportTest {

    @Test
    void testGetTypeHappyPath() {
        final String expectedType = "automobile";

        final Transport transport = new Transport("automobile", null);
        final String actualType = transport.getType();

        assertNotNull(actualType, "String is null");
        assertEquals(actualType, expectedType);
    }

    @Test
    void testGetModelHappyPath() {
        final String expectedModel = "BMW M5";

        final Transport transport = new Transport(null, "BMW M5");
        final String actualModel = transport.getModel();

        assertNotNull(actualModel, "String is null");
        assertEquals(actualModel, expectedModel);
    }
}
