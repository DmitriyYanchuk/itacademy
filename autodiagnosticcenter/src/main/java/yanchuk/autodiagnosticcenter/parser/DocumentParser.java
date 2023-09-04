package yanchuk.autodiagnosticcenter.parser;

import java.util.List;

public interface DocumentParser {

    List<String> parse() throws DocumentParserException;
}
