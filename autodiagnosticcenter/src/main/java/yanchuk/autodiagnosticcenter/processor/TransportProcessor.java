package yanchuk.autodiagnosticcenter.processor;

import yanchuk.autodiagnosticcenter.transport.Transport;

import java.util.List;
import java.util.function.Function;

public class TransportProcessor implements Processor {

    @Override
    public final List<String> processProcessedTransport(final List<Transport> transport) throws ProcessorException {
        if (transport == null) {
            throw new ProcessorException("The list being modified does not exist");
        } else {
            return process(transport, TransportProcessor::processedTransport);
        }
    }

    @Override
    public final List<String> processInvalidTransport(final List<Transport> transport) throws ProcessorException {
        if (transport == null) {
            throw new ProcessorException("The list being modified does not exist");
        } else {
            return process(transport, TransportProcessor::invalidTransport);
        }
    }

    private List<String> process(final List<Transport> transport, final Function<Transport, String> convert) {
        return transport.stream()
                .map(convert)
                .toList();
    }

    private static String processedTransport(final Transport transport) {
        final String cost;
        final String type = transport.getType();

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

        return type + ", " + transport.getModel() + cost;
    }

    private static String invalidTransport(final Transport transport) {
        return transport.getType() + ", " + transport.getModel() + ",  ";
    }
}
