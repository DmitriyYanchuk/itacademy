package yanchuk.autodiagnosticcenter.writer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonTransportWriterTest {

    @Test
    void testInvalidTransportSuccessfulWriting() throws TransportWriterException, IOException {
        final List<String> invalidTransportList = new ArrayList<>();
        invalidTransportList.add("automobile, Audi Q9!№,  ");
        invalidTransportList.add("motorbike, Ninja **,  ");

        final File comparableFile = new File("src/test/java/resources/comparableInvalidFile.json");
        final String comparableString = Files.readString(comparableFile.toPath());

        final String fileName = "invalid-transport.json";
        final File invalidTransport = new File(fileName);

        final JsonTransportWriter writer = new JsonTransportWriter("processed-transport.json", fileName);
        writer.invalidTransport(invalidTransportList);
        final String invalidString = Files.readString(Path.of(fileName));

        assertNotNull(invalidTransport, "File is null");
        assertEquals(invalidString, comparableString);
    }

    @Test
    void testProcessedTransportSuccessfulWriting() throws TransportWriterException, IOException {
        final List<String> processedTransportList = new ArrayList<>();
        processedTransportList.add("automobile, Audi Q7, 20");
        processedTransportList.add("motorbike, Ninja ZX-14, 10");

        final File comparableFile = new File("src/test/java/resources/comparableProcessedFile.json");
        final String comparableString = Files.readString(comparableFile.toPath());

        final String fileName = "processed-transport.json";
        final File invalidTransport = new File(fileName);

        final JsonTransportWriter writer = new JsonTransportWriter(fileName, "invalid-transport.json");
        writer.processedTransport(processedTransportList);
        final String invalidString = Files.readString(Path.of(fileName));

        assertNotNull(invalidTransport, "File is null");
        assertEquals(invalidString, comparableString);
    }

    @Test
    void testInvalidTransportWriterException() {
        final List<String> invalidTransport = null;
        final JsonTransportWriter writer = new JsonTransportWriter("processed-transport.json", "invalid-transport.json");

        final var exception = assertThrows(TransportWriterException.class, () -> writer.invalidTransport(invalidTransport));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }

    @Test
    void testProcessedTransportWriterException() {
        final List<String> processedTransport = null;
        final JsonTransportWriter writer = new JsonTransportWriter("processed-transport.json", "invalid-transport.json");

        final var exception = assertThrows(TransportWriterException.class, () -> writer.processedTransport(processedTransport));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }

    @Test
    void testInvalidTransportWriterExceptionFileWriteError() {
        final List<String> invalidTransportList = new ArrayList<>();
        invalidTransportList.add("automobile, Audi Q9!№,  ");
        invalidTransportList.add("motorbike, Ninja **,  ");

        final JsonTransportWriter writer = new JsonTransportWriter("processed-transport.json", "cars/invalid-transport.json");

        final var exception = assertThrows(TransportWriterException.class, () -> writer.invalidTransport(invalidTransportList));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("File write error", exception.getMessage());
    }

    @Test
    void testProcessedTransportWriterExceptionFileWriteError() {
        final List<String> processedTransportList = new ArrayList<>();
        processedTransportList.add("automobile, Audi Q7, 20");
        processedTransportList.add("motorbike, Ninja ZX-14, 10");

        final JsonTransportWriter writer = new JsonTransportWriter("cars/processed-transport.json", "invalid-transport.json");

        final var exception = assertThrows(TransportWriterException.class, () -> writer.processedTransport(processedTransportList));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("File write error", exception.getMessage());
    }
}
