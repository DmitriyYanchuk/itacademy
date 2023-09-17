package yanchuk.autodiagnosticcenter.validator;

import yanchuk.autodiagnosticcenter.annotations.Parameter;
import yanchuk.autodiagnosticcenter.transport.Transport;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ParameterValidationProcessor implements ValidationProcessor {

    @Override
    public final List<Transport> validateProcessedTransport(final List<Transport> transports) throws ValidationProcessorException {
        return validateTransport(transports, true);
    }

    @Override
    public final List<Transport> validateInvalidTransport(final List<Transport> transports) throws ValidationProcessorException {
        return validateTransport(transports, false);
    }

    private List<Transport> validateTransport(
            final List<Transport> transports,
            final boolean verifyValid
    ) throws ValidationProcessorException {
        final List<Transport> validatedTransport = new ArrayList<>(transports.size());

        for (final Transport transport : transports) {
            if (verifyValid == isValid(transport)) {
                validatedTransport.add(transport);
            }
        }

        return validatedTransport;
    }

    private static boolean isValid(final Transport transport) throws ValidationProcessorException {
        boolean isValid = false;

        for (final Field field : transport.getClass().getDeclaredFields()) {

            for (final Annotation annotation : field.getDeclaredAnnotations()) {
                if (!(annotation instanceof Parameter)) {
                    continue;
                }

                if (!field.canAccess(transport) && !field.trySetAccessible()) {
                    continue;
                }

                final Parameter parameter = (Parameter) annotation;

                try {
                    final Object fieldType = field.get(transport);
                    if (!(fieldType instanceof String)) {
                        continue;
                    }

                    final String model = (String) fieldType;
                    final Predicate<String> predicate = Pattern.compile(parameter.pattern()).asPredicate();

                    isValid = predicate.test(model);

                } catch (final IllegalAccessException exc) {
                    throw new ValidationProcessorException("Failed to process Parameter annotation", exc);
                }

            }
        }
        return isValid;
    }
}
