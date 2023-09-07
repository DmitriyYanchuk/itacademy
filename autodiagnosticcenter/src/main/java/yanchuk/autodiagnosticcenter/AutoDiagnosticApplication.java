package yanchuk.autodiagnosticcenter;

import yanchuk.autodiagnosticcenter.console.ConsoleParameters;
import yanchuk.autodiagnosticcenter.console.ConsoleSorting;
import yanchuk.autodiagnosticcenter.editor.TransportList;
import yanchuk.autodiagnosticcenter.parser.DocumentParser;
import yanchuk.autodiagnosticcenter.parser.JsonDocumentParser;
import yanchuk.autodiagnosticcenter.editor.ListTransportEditor;
import yanchuk.autodiagnosticcenter.console.ConsoleSortingParameters;
import yanchuk.autodiagnosticcenter.sorter.TransportSorter;
import yanchuk.autodiagnosticcenter.writer.JsonTransportWriter;
import yanchuk.autodiagnosticcenter.writer.TransportWriter;

import java.util.List;

public class AutoDiagnosticApplication {
    public static void main(String[] args) {
        System.out.println("Start of the working Auto Diagnostic Application");

        try {
            final DocumentParser json = new JsonDocumentParser("transport.json");
            final List<String> jsonList = json.parse();

            final TransportList editor = new ListTransportEditor();
            final List<String> processedTransport = editor.processedList(jsonList);
            final List<String> invalidTransport = editor.invalidList(jsonList);

            final ConsoleSorting consoleSorting = new ConsoleSorting();
            final ConsoleParameters parameters = new ConsoleSortingParameters(consoleSorting);
            final int sortingType = parameters.sortingType();
            final int sortingOrder = parameters.sortingOrder();

            final TransportSorter sorter = new TransportSorter(sortingType, sortingOrder);
            final List<String> sortedTransport = sorter.sorting(processedTransport);

            final TransportWriter writer = new JsonTransportWriter();
            writer.writeTransportFile(sortedTransport, "processed-transport.json");
            writer.writeTransportFile(invalidTransport, "invalid-transport.json");

        } catch (final Exception exc) {
            System.err.println("Program error " + exc.getMessage());
            exc.printStackTrace();
        }

        System.out.println("End of the working Auto Diagnostic Application");
    }
}
