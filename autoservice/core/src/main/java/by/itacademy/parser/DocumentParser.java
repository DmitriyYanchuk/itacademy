package by.itacademy.parser;

import by.itacademy.transport.Transport;

import java.util.List;

public interface DocumentParser {

    List<Transport> parse(String content) throws DocumentParserException;
}
