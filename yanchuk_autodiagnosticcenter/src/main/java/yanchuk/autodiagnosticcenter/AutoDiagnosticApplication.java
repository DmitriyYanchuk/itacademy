package yanchuk.autodiagnosticcenter;

import yanchuk.autodiagnosticcenter.parser.JsonDocumentParser;
import yanchuk.autodiagnosticcenter.editor.ListTransportEditor;
import yanchuk.autodiagnosticcenter.sorter.ConsoleSortingParameters;
import yanchuk.autodiagnosticcenter.sorter.TransportSorter;
import yanchuk.autodiagnosticcenter.writer.JsonTransportWriter;

import java.util.List;

public class AutoDiagnosticApplication {
    public static void main(String[] args) {
        System.out.println("Start of the working Auto Diagnostic Application");

        try {
            final JsonDocumentParser json = new JsonDocumentParser("transport.json");
            final List<String> jsonList = json.parse();

            final ListTransportEditor editor = new ListTransportEditor();
            final List<String> validTransport = editor.validList(jsonList);
            final List<String> invalidTransport = editor.invalidList(jsonList);

            final ConsoleSortingParameters parameters = new ConsoleSortingParameters();
            final int sortingType = parameters.sortingType();
            final int sortingOrder = parameters.sortingOrder();

            final TransportSorter sorter = new TransportSorter(sortingType, sortingOrder);
            final List<String> sortedTransport = sorter.sorting(validTransport);

            final JsonTransportWriter writer = new JsonTransportWriter("processed-transport.json", "invalid-transport.json");
            writer.processedTransport(sortedTransport);
            writer.invalidTransport(invalidTransport);

        } catch (final Exception exc) {
            System.err.println("Program error " + exc.getMessage());
            exc.printStackTrace();
        }

        System.out.println("End of the working Auto Diagnostic Application");
    }
}
