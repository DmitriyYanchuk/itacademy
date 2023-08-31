package yanchuk.autodiagnosticcenter.writer;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonTransportWriter implements TransportWriter {

    private final File processedTransportFile;
    private final File invalidTransportFile;

    public JsonTransportWriter(final String processedTransportFile, final String invalidTransportFile) {
        this.processedTransportFile = new File(processedTransportFile);
        this.invalidTransportFile = new File(invalidTransportFile);
    }

    @Override
    public void invalidTransport(final List<String> invalidList) throws TransportWriterException {
        try {
            if (invalidList.isEmpty()) {
                System.out.println("File with invalid transport is empty");
            } else {
                write(invalidTransportFile, invalidList);
                System.out.println("Invalid file is created");
            }

        } catch (final RuntimeException exc) {
            throw new TransportWriterException("The list being modified does not exist", exc);
        }
    }

    @Override
    public void processedTransport(final List<String> processedList) throws TransportWriterException {
        try {
            if (processedList.isEmpty()) {
                System.out.println("Not valid transport in file");
            } else {
                write(processedTransportFile, processedList);
                System.out.println("Processed file is created");
                System.out.println(processedTransportFile.getAbsolutePath());
            }

        } catch (final RuntimeException exc) {
            throw new TransportWriterException("The list being modified does not exist", exc);
        }
    }

    private void write(final File file, final List<String> objects) throws TransportWriterException {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            final List<JSONObject> jsonList = new ArrayList<>();

            for (String transport : objects) {
                final JSONObject jsonObject = new JSONObject();
                final String[] parts = transport.split("\\,\\s");
                jsonObject.put("type", parts[0]);
                jsonObject.put("model", parts[1]);

                if (!parts[2].isBlank()) {
                    jsonObject.put("cost", parts[2]);
                }

                jsonList.add(jsonObject);
            }
            writer.write(jsonList.toString());
        } catch (final IOException e) {
            throw new TransportWriterException("File write error", e);
        }
    }
}
