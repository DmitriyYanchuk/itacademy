package it.academy;

import it.academy.entity.client.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    final Client client = new Client(5, "Agramant", "Bourgouin");

    @Test
    void testGetClientFirstName_happyPath() {
        final String expectedFirstName = "Agramant";
        final String actualFirstName = client.getFirstName();

        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    void testGetClientLastName_happyPath() {
        final String expectedLastName = "Bourgouin";
        final String actualLastName = client.getLastName();

        assertEquals(expectedLastName, actualLastName);
    }

    @Test
    void testGetClientId_happyPath() {
        final int expectedId = 5;
        final int actualId = client.getId();

        assertEquals(expectedId, actualId);
    }
}
