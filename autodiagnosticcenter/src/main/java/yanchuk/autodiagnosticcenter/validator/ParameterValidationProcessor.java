package yanchuk.autodiagnosticcenter.validator;

import yanchuk.autodiagnosticcenter.annotations.Parameter;
import yanchuk.autodiagnosticcenter.transport.Transport;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ParameterValidationProcessor implements ValidationProcessor {

    @Override
    public final List<Transport> processedTransportList(final List<Transport> transport) throws ValidationProcessorException {
        return transportProcess(transport, transport1 -> {
            try {
                return validTransport(transport1);
            } catch (ValidationProcessorException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public final List<Transport> invalidTransportList(final List<Transport> transport) throws ValidationProcessorException {
        return transportProcess(transport, transport1 -> {
            try {
                return invalidTransport(transport1);
            } catch (ValidationProcessorException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private List<Transport> transportProcess(
            final List<Transport> transport,
            final Function<Transport, Transport> convert
    ) {
        return transport.stream()
                .map(convert)
                .filter(Objects::nonNull)
                .toList();
    }

    private static Transport invalidTransport(final Transport transport) throws ValidationProcessorException {
        return isValid(transport) ? null : transport;
    }

    private static Transport validTransport(final Transport transport) throws ValidationProcessorException {
        return !isValid(transport) ? null : transport;
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
