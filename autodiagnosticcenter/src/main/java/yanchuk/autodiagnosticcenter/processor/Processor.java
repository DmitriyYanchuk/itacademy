package yanchuk.autodiagnosticcenter.processor;

import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.List;

public interface Processor {

    List<String> processProcessedTransport(List<Transport> transport) throws ProcessorException;
    List<String> processInvalidTransport(List<Transport> transport) throws ProcessorException;
}
