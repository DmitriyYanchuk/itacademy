package yanchuk.autodiagnosticcenter.writer;

public class TransportWriterException extends Exception {

    public TransportWriterException(final String message, final Throwable exc) {
        super(message, exc);
    }
}
