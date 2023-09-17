package yanchuk.autodiagnosticcenter.validator;

import org.junit.jupiter.api.Test;
import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParameterValidationProcessorTest {

    @Test
    void testValidateProcessedTransportSuccessfully() throws ValidationProcessorException {
        final List<String> comparableList = new ArrayList<>();
        comparableList.add("automobile, Audi Q7");
        comparableList.add("minibus, Sprinter264");
        comparableList.add("minibus, Transporter T5");
        comparableList.add("automobile, BMW M5");

        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport("automobile", "Audi Q7"));
        transportList.add(new Transport("minibus", "Sprinter264"));
        transportList.add(new Transport("automobile", "Audi Q9!№"));
        transportList.add(new Transport("minibus", "Transporter T5"));
        transportList.add(new Transport("automobile", "BMW M5"));

        final ValidationProcessor validationProcessor = new ParameterValidationProcessor();
        final List<Transport> validateTransport = validationProcessor.validateProcessedTransport(transportList);
        final List<String> actualList = new ArrayList<>();

        for (final Transport transport : validateTransport) {
            final String actual = transport.getType() + ", " + transport.getModel();
            actualList.add(actual);
        }

        assertNotNull(validateTransport, "Array is bull");
        assertEquals(actualList, comparableList);
    }

    @Test
    void testValidateInvalidTransportSuccessfully() throws ValidationProcessorException {
        final List<String> comparableList = new ArrayList<>();
        comparableList.add("automobile, Audi Q9!№");
        comparableList.add("motorbike, Ninja **");

        final List<Transport> transportList = new ArrayList<>();
        transportList.add(new Transport("automobile", "Audi Q7"));
        transportList.add(new Transport("minibus", "Sprinter264"));
        transportList.add(new Transport("automobile", "Audi Q9!№"));
        transportList.add(new Transport("minibus", "Transporter T5"));
        transportList.add(new Transport("motorbike", "Ninja **"));

        final ValidationProcessor validationProcessor = new ParameterValidationProcessor();
        final List<Transport> validateTransport = validationProcessor.validateInvalidTransport(transportList);
        final List<String> actualList = new ArrayList<>();

        for (final Transport transport : validateTransport) {
            final String actual = transport.getType() + ", " + transport.getModel();
            actualList.add(actual);
        }

        assertNotNull(validateTransport, "Array is bull");
        assertEquals(actualList, comparableList);
    }
}
