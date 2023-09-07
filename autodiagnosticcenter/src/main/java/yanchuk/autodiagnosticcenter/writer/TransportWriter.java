package yanchuk.autodiagnosticcenter.writer;

import java.util.List;

public interface TransportWriter {

    void writeTransportFile(List<String> invalidList, String fileName) throws TransportWriterException;
}
