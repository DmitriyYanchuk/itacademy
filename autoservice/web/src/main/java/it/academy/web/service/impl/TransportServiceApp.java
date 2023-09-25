package it.academy.web.service.impl;

import by.itacademy.parser.DocumentParser;
import by.itacademy.parser.DocumentParserException;
import by.itacademy.parser.impl.JsonDocumentParser;
import by.itacademy.processor.Processor;
import by.itacademy.processor.ProcessorException;
import by.itacademy.processor.imp.TransportProcessor;
import by.itacademy.sorter.SortingList;
import by.itacademy.sorter.SortingListException;
import by.itacademy.sorter.impl.TransportSorter;
import by.itacademy.transport.Transport;
import by.itacademy.validator.ValidationProcessor;
import by.itacademy.validator.ValidationProcessorException;
import by.itacademy.validator.impl.ParameterValidationProcessor;
import it.academy.web.decoder.Decoder;
import it.academy.web.decoder.impl.ContentDecoder;
import it.academy.web.response.Response;
import it.academy.web.response.impl.HtmlResponse;
import it.academy.web.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TransportServiceApp implements Service {

    @Override
    public void startProgram(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final Decoder decoder = new ContentDecoder(request);

        try {
            final DocumentParser parser = new JsonDocumentParser();
            final List<Transport> transportList = parser.parse(decoder.getDecodedContent());

            final ValidationProcessor validationProcessor = new ParameterValidationProcessor();
            final List<Transport> approvedProcessesTransport =
                    validationProcessor.validateProcessedTransport(transportList);
            final List<Transport> approvedInvalidTransport =
                    validationProcessor.validateInvalidTransport(transportList);

            final Processor processor = new TransportProcessor();
            final List<String> modifiedProcessedTransport =
                    processor.processProcessedTransport(approvedProcessesTransport);
            final List<String> modifiedInvalidTransport =
                    processor.processInvalidTransport(approvedInvalidTransport);

            final String sortingType = request.getParameter("sortingType");
            final int typeParameter = Integer.parseInt(sortingType);
            final String sortingOrder = request.getParameter("sortingOrder");
            final int orderParameter = Integer.parseInt(sortingOrder);

            final SortingList sortingList = new TransportSorter(typeParameter, orderParameter);
            final List<String> sortedTransport = sortingList.sorting(modifiedProcessedTransport);

            final Response responseServer = new HtmlResponse();
            final String processedTransport = getWriter(sortedTransport, true,
                    "Processed-Transport", responseServer);
            final String invalidTransport = getWriter(modifiedInvalidTransport, false,
                    "Invalid-Transport", responseServer);
            response.getWriter().println(processedTransport);
            response.getWriter().println(invalidTransport);

        } catch (final DocumentParserException exc) {
            throw new RuntimeException("JSON parsing failed", exc);
        } catch (final ValidationProcessorException exc) {
            throw new RuntimeException("Failed to validation", exc);
        } catch (final ProcessorException exc) {
            throw new RuntimeException("The list being modified does not exist", exc);
        } catch (final SortingListException exc) {
            throw new RuntimeException("Sorting error", exc);
        }
    }

    private static String getWriter(
            final List<String> transports, final boolean isValid,
            final String columnName, Response response
    ) {
        return response.response(transports, isValid, columnName);
    }
}
