package yanchuk.autodiagnosticcenter.editor;

import java.util.List;

public interface TransportList {

    List<String> processedList(List<String> list) throws ListTransportException;
    List<String> invalidList(List<String> list) throws ListTransportException;
}
