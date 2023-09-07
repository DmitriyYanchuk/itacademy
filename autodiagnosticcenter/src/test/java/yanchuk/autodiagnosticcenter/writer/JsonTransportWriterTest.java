package yanchuk.autodiagnosticcenter.writer;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class JsonTransportWriterTest {

    @Test
    void testInvalidTransportSuccessfulWriting() throws TransportWriterException, IOException {
        final List<String> invalidTransportList = new ArrayList<>();
        invalidTransportList.add("automobile, Audi Q9!№,  ");
        invalidTransportList.add("motorbike, Ninja **,  ");

        final InputStream on = getClass().getClassLoader().getResourceAsStream("comparableInvalidFile.json");
        final BufferedReader comparableReader = new BufferedReader(new InputStreamReader(on, StandardCharsets.UTF_8));
        final String comparableString = comparableReader.lines().collect(Collectors.joining());

        final String fileName = "invalid-transport.json";
        final File invalidTransport = new File(fileName);

        final JsonTransportWriter writer = new JsonTransportWriter();
        writer.writeTransportFile(invalidTransportList, fileName);
        final String invalidString = Files.readString(Path.of(fileName));

        assertNotNull(invalidTransport, "File is null");
        assertEquals(invalidString, comparableString);
    }

    @Test
    void testProcessedTransportSuccessfulWriting() throws TransportWriterException, IOException {
        final List<String> processedTransportList = new ArrayList<>();
        processedTransportList.add("automobile, Audi Q7, 20");
        processedTransportList.add("motorbike, Ninja ZX-14, 10");

        final InputStream on = getClass().getClassLoader().getResourceAsStream("comparableProcessedFile.json");
        final BufferedReader comparableReader = new BufferedReader(new InputStreamReader(on, StandardCharsets.UTF_8));
        final String comparableString = comparableReader.lines().collect(Collectors.joining());

        final String fileName = "processed-transport.json";
        final File invalidTransport = new File(fileName);

        final JsonTransportWriter writer = new JsonTransportWriter();
        writer.writeTransportFile(processedTransportList, fileName);
        final String invalidString = Files.readString(Path.of(fileName));

        assertNotNull(invalidTransport, "File is null");
        assertEquals(invalidString, comparableString);
    }

    @Test
    void testWritingTransportFileIsNullThrowsException() {
        final JsonTransportWriter writer = new JsonTransportWriter();

        final Throwable exception = assertThrows(TransportWriterException.class,
                () -> writer.writeTransportFile(null, "transportFile.json"));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }

    @Test
    void testWritingTransportFileFileWriteException() {
        final List<String> transportList = new ArrayList<>();
        transportList.add("automobile, Audi Q9!№,  ");
        transportList.add("motorbike, Ninja **,  ");

        final JsonTransportWriter writer = new JsonTransportWriter();

        final Throwable exception = assertThrows(TransportWriterException.class,
                () -> writer.writeTransportFile(transportList, "cars/invalid-transport.json"));

        assertNotNull(exception, "TransportWriterException is null");
        assertEquals("File write error", exception.getMessage());
    }
}
