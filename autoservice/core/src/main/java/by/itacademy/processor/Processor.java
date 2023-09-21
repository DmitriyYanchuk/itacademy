package by.itacademy.processor;

import by.itacademy.transport.Transport;

import java.util.List;

public interface Processor {

    List<String> processProcessedTransport(List<Transport> transport) throws ProcessorException;
    List<String> processInvalidTransport(List<Transport> transport) throws ProcessorException;
}
