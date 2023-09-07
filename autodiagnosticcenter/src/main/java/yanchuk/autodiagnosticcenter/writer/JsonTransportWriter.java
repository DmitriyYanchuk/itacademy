package yanchuk.autodiagnosticcenter.writer;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonTransportWriter implements TransportWriter {

    @Override
    public void writeTransportFile(final List<String> transportList, final String fileName) throws TransportWriterException {
        try {
            if (transportList.isEmpty()) {
                System.out.println("File with " + fileName + " transport is empty");
            } else {
                final File transportFile = new File(fileName);
                write(transportFile, transportList);
                System.out.println(fileName + " file is created");
                System.out.println(transportFile.getAbsolutePath());
            }
        } catch (final RuntimeException exc) {
            throw new TransportWriterException("The list being modified does not exist", exc);
        }
    }

    private void write(final File file, final List<String> objects) throws TransportWriterException {
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            final List<JSONObject> jsonList = new ArrayList<>();

            for (final String transport : objects) {
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
        } catch (final IOException exc) {
            throw new TransportWriterException("File write error", exc);
        }
    }
}
