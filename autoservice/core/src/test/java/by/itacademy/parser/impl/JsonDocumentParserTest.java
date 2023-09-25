package by.itacademy.parser.impl;

import by.itacademy.parser.DocumentParserException;
import by.itacademy.transport.Transport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonDocumentParserTest {

    @Test
    void testListParsingSuccessfully() throws DocumentParserException {
        final List<Transport> comparableList = new ArrayList<>();
        comparableList.add(new Transport("motorbike", "Ninja ZX-14"));
        comparableList.add(new Transport("automobile", "Audi Q9!№"));
        comparableList.add(new Transport("minibus", "Sprinter264"));

        final String content = "[{\"model\":\"Ninja ZX-14\",\"type\":\"motorbike\"}," +
                "{\"model\":\"Audi Q9!№\",\"type\":\"automobile\"}," +
                "{\"model\":\"Sprinter264\",\"type\":\"minibus\"}]";
        final JsonDocumentParser jsonParser = new JsonDocumentParser();
        final List<Transport> actualList = jsonParser.parse(content);

        assertNotNull(actualList, "Array is null");
        assertEquals(actualList, comparableList);
    }

    @Test
    void testJSONParsingFailedThrowsJSONException() {
        final JsonDocumentParser jsonParser = new JsonDocumentParser();

        final String content = "[{\"sort\":\"Ninja ZX-14\",\"type\":\"motorbike\"}," +
                "{\"sort\":\"Audi Q9!№\",\"type\":\"automobile\"}," +
                "{\"sort\":\"Sprinter264\",\"type\":\"minibus\"}]";

        final Throwable exception = assertThrows(DocumentParserException.class, () -> jsonParser.parse(content));

        assertNotNull(exception, "DocumentParserException is null");
        assertEquals("JSON parsing failed", exception.getMessage());
    }
}
