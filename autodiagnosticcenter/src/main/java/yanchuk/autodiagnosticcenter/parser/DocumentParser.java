package yanchuk.autodiagnosticcenter.parser;

import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.List;

public interface DocumentParser {

    List<Transport> parse() throws DocumentParserException;
}
