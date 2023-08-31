package yanchuk.autodiagnosticcenter.parser;

public class DocumentParserException extends Exception {

    public DocumentParserException(final String message, final Throwable exc) {
        super(message, exc);
    }

    public DocumentParserException(final String message) {
        super(message);
    }
}
