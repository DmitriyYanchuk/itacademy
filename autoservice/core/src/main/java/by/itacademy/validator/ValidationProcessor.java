package by.itacademy.validator;

import by.itacademy.transport.Transport;

import java.util.List;

public interface ValidationProcessor {

    List<Transport> validateProcessedTransport(List<Transport> transport) throws ValidationProcessorException;

    List<Transport> validateInvalidTransport(List<Transport> transport) throws ValidationProcessorException;
}
