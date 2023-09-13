package yanchuk.autodiagnosticcenter.processor;

import org.junit.jupiter.api.Test;
import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportProcessorTest {

    @Test
    void testValidListSuccessfulCompilation() throws ProcessorException {
        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport("motorbike", "Ninja ZX-14"));
        transportList.add(new Transport("automobile", "Audi Q7"));

        final List<String> comparableList = new ArrayList<>();
        comparableList.add("motorbike, Ninja ZX-14, 10");
        comparableList.add("automobile, Audi Q7, 20");

        final Processor processor = new TransportProcessor();
        final List<String> processedList = processor.processProcessedTransport(transportList);

        assertNotNull(processedList, "Array is null");
        assertEquals(processedList, comparableList);
    }

    @Test
    void testInvalidListSuccessfulCompilation() throws ProcessorException {
        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport("automobile", "Audi Q9!№"));
        transportList.add(new Transport("motorbike", "Ninja **"));

        final List<String> comparableList = new ArrayList<>();
        comparableList.add("automobile, Audi Q9!№,  ");
        comparableList.add("motorbike, Ninja **,  ");

        final Processor processor = new TransportProcessor();
        final List<String> invalidList = processor.processInvalidTransport(transportList);

        assertNotNull(invalidList, "Array is null");
        assertEquals(invalidList, comparableList);
    }

    @Test
    void testProcessedTransportListIsNullThrowsException() {
        final Processor processor = new TransportProcessor();

        final Throwable exception = assertThrows(ProcessorException.class,
                () -> processor.processProcessedTransport(null));

        assertNotNull(exception, "ProcessorException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }

    @Test
    void testInvalidTransportListIsNullThrowsException() {
        final Processor processor = new TransportProcessor();

        final Throwable exception = assertThrows(ProcessorException.class,
                () -> processor.processInvalidTransport(null));

        assertNotNull(exception, "ProcessorException is null");
        assertEquals("The list being modified does not exist", exception.getMessage());
    }
}