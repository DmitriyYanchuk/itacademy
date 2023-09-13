package yanchuk.autodiagnosticcenter.validator;

public class ValidationProcessorException extends Exception {

    public ValidationProcessorException(final String message, final Throwable exc) {
        super(message, exc);
    }
}
