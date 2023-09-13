package yanchuk.autodiagnosticcenter.processor;

import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.List;

public interface Processor {

    List<String> processedStringList(List<Transport> transport) throws ProcessorException;
    List<String> invalidStringList(List<Transport> transport) throws ProcessorException;
}
