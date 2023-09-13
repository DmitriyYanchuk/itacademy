package yanchuk.autodiagnosticcenter.validator;

import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.List;

public interface ValidationProcessor {

    List<Transport> processedTransportList(List<Transport> transport) throws ValidationProcessorException;

    List<Transport> invalidTransportList(List<Transport> transport) throws ValidationProcessorException;
}
