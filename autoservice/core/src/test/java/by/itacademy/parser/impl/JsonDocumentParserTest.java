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
        comparableList.add(new Transport("automobile", "Audi Q7"));
        comparableList.add(new Transport("automobile", "Audi Q9!№"));
        comparableList.add(new Transport("minibus", "Sprinter264"));
        comparableList.add(new Transport("minibus", "Transporter T5"));
        comparableList.add(new Transport("automobile", "BMW M5"));
        comparableList.add(new Transport("automobile", "Mazda CX7"));
        comparableList.add(new Transport("motorbike", "Ninja **"));

        final JsonDocumentParser jsonParser = new JsonDocumentParser("transport.json");
        final List<Transport> actualList = jsonParser.parse();

        assertNotNull(actualList, "Array is null");
        assertEquals(actualList, comparableList);
    }

    @Test
    void testJSONParsingFailedThrowsJSONException() {
        final JsonDocumentParser jsonParser = new JsonDocumentParser("transportJSONParsingFailed.json");

        final Throwable exception = assertThrows(DocumentParserException.class, jsonParser::parse);

        assertNotNull(exception, "DocumentParserException is null");
        assertEquals("JSON parsing failed", exception.getMessage());
    }

    @Test
    void testJSONParsingGetContentThrowsReadingException() {
        final JsonDocumentParser jsonParser = new JsonDocumentParser("cars.json");

        final Throwable exception = assertThrows(DocumentParserException.class, jsonParser::parse);

        assertNotNull(exception, "DocumentParserException is null");
        assertEquals("Reading file error", exception.getMessage());
    }
}
