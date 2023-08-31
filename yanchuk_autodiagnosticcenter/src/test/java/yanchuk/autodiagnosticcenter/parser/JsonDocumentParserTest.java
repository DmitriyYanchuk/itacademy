package yanchuk.autodiagnosticcenter.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDocumentParserTest {

    @Test
    void testListParsingSuccessfully() throws DocumentParserException {
        final List<String> comparableList = new ArrayList<>();
        comparableList.add("motorbike, Ninja ZX-14");
        comparableList.add("automobile, Audi Q7");
        comparableList.add("automobile, Audi Q9!№");
        comparableList.add("minibus, Sprinter264");
        comparableList.add("minibus, Transporter T5");
        comparableList.add("automobile, BMW M5");
        comparableList.add("automobile, Mazda CX7");
        comparableList.add("motorbike, Ninja **");

        final JsonDocumentParser jsonParser = new JsonDocumentParser("src/test/java/resources/transport.json");
        final List<String> allTransport = jsonParser.parse();

        assertNotNull(allTransport, "Array is null");
        assertEquals(allTransport, comparableList);
    }

    @Test
    void testJSONParsingFailed() {
        final JsonDocumentParser jsonParser = new JsonDocumentParser("src/test/java/resources/transportJSONParsingFailed.json");

        final var exception = assertThrows(DocumentParserException.class, () -> jsonParser.parse());

        assertNotNull(exception, "DocumentParserException is null");
        assertEquals("JSON parsing failed", exception.getMessage());
    }

    @Test
    void testFileNotFound() {
        final JsonDocumentParser jsonParser = new JsonDocumentParser("cars.json");

        final var exception = assertThrows(DocumentParserException.class, () -> jsonParser.parse());

        assertNotNull(exception, "DocumentParserException is null");
        assertEquals("File " + "cars.json" + " not found", exception.getMessage());
    }
}
