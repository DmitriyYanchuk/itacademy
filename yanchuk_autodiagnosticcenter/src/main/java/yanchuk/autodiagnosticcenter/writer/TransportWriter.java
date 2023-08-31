package yanchuk.autodiagnosticcenter.writer;

import java.util.List;

public interface TransportWriter {

    void invalidTransport(List<String> invalidList) throws TransportWriterException;

    void processedTransport(List<String> processedList) throws TransportWriterException;
}
