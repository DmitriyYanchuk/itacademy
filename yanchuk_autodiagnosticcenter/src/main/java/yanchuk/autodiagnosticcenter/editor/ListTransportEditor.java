package yanchuk.autodiagnosticcenter.editor;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;


public class ListTransportEditor implements TransportList {

    private static final Predicate<String> MODEL_VALIDATOR = Pattern.compile("^[a-zA-Z]((\\s|-)?[a-zA-Z0-9])*$").asPredicate();

    @Override
    public final List<String> validList(final List<String> jsonList) throws ListTransportException {
        try {
            return jsonList.stream()
                    .map(ListTransportEditor::validObjects)
                    .filter(Objects::nonNull)
                    .toList();

        } catch (final RuntimeException exc) {
            throw new ListTransportException ("The list being modified does not exist", exc);
        }
    }

    @Override
    public final List<String> invalidList(final List<String> jsonList) throws ListTransportException {
        try {
            return jsonList.stream()
                    .map(ListTransportEditor::invalidObjects)
                    .filter(Objects::nonNull)
                    .toList();

        } catch (final RuntimeException exc) {
            throw new ListTransportException ("The list being modified does not exist", exc);
        }
    }

    private static String validObjects(String transport) {
        final String[] parts = transport.split("\\,\\s");

        if (isValid(transport)) {
            final String type = parts[0];
            transport = transport + forValidObjects(type);
        } else {
            transport = null;
        }

        return transport;
    }

    private static String forValidObjects(final String type) {
        final String cost;

        if (type.equals("motorbike")) {
            cost = ", 10";
        } else if (type.equals("automobile")) {
            cost = ", 20";
        } else if (type.equals("minibus")) {
            cost = ", 30";
        } else {
            System.out.println("The type does not exist in the match tree");
            cost = null;
        }

        return cost;
    }

    private static String invalidObjects(String transport) {
        if (!isValid(transport)) {
            transport = forInvalidObjects(transport);
        } else {
            transport = null;
        }

        return transport;
    }

    private static String forInvalidObjects(final String transport) {
        return transport + ",  ";
    }

    private static boolean isValid(final String transport) {
        final String[] parts = transport.split("\\,\\s");
        final String model = parts[1];
        final boolean isValid = MODEL_VALIDATOR.test(model);

        return isValid;
    }
}
