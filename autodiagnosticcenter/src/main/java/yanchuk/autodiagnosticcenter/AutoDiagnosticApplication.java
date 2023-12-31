package yanchuk.autodiagnosticcenter;

import yanchuk.autodiagnosticcenter.console.ConsoleParameters;
import yanchuk.autodiagnosticcenter.console.ConsoleSorting;
import yanchuk.autodiagnosticcenter.processor.Processor;
import yanchuk.autodiagnosticcenter.parser.DocumentParser;
import yanchuk.autodiagnosticcenter.parser.JsonDocumentParser;
import yanchuk.autodiagnosticcenter.processor.TransportProcessor;
import yanchuk.autodiagnosticcenter.console.ConsoleSortingParameters;
import yanchuk.autodiagnosticcenter.sorter.TransportSorter;
import yanchuk.autodiagnosticcenter.transport.Transport;
import yanchuk.autodiagnosticcenter.validator.ParameterValidationProcessor;
import yanchuk.autodiagnosticcenter.validator.ValidationProcessor;
import yanchuk.autodiagnosticcenter.writer.JsonTransportWriter;
import yanchuk.autodiagnosticcenter.writer.TransportWriter;

import java.util.List;

public class AutoDiagnosticApplication {
    public static void main(String[] args) {
        System.out.println("Start of the working Auto Diagnostic Application");

        try {
            final DocumentParser json = new JsonDocumentParser("transport.json");
            final List<Transport> jsonList = json.parse();

            final ValidationProcessor validationProcessor = new ParameterValidationProcessor();
            final List<Transport> approvedProcessedTransport =
                    validationProcessor.validateProcessedTransport(jsonList);
            final List<Transport> approvedInvalidTransport =
                    validationProcessor.validateInvalidTransport(jsonList);

            final Processor processor = new TransportProcessor();
            final List<String> modifiedProcessedTransport =
                    processor.processProcessedTransport(approvedProcessedTransport);
            final List<String> modifiedInvalidTransport =
                    processor.processInvalidTransport(approvedInvalidTransport);

            final ConsoleSorting consoleSorting = new ConsoleSorting();
            final ConsoleParameters parameters = new ConsoleSortingParameters(consoleSorting);
            final int sortingType = parameters.sortingType();
            final int sortingOrder = parameters.sortingOrder();

            final TransportSorter sorter = new TransportSorter(sortingType, sortingOrder);
            final List<String> sortedTransport = sorter.sorting(modifiedProcessedTransport);

            final TransportWriter writer = new JsonTransportWriter();
            writer.writeTransportFile(sortedTransport, "processed-transport.json");
            writer.writeTransportFile(modifiedInvalidTransport, "invalid-transport.json");

        } catch (final Exception exc) {
            System.err.println("Program error " + exc.getMessage());
            exc.printStackTrace();
        }

        System.out.println("End of the working Auto Diagnostic Application");
    }
}
