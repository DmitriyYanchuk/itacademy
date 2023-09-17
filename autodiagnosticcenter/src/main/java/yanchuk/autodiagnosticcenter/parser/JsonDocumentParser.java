package yanchuk.autodiagnosticcenter.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import yanchuk.autodiagnosticcenter.transport.Transport;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDocumentParser implements DocumentParser {

    private final String fileName;

    public JsonDocumentParser(final String name) {
        this.fileName = name;
    }

    @Override
    public final List<Transport> parse() throws DocumentParserException {
        try {
            final List<Transport> allTransport = new ArrayList<>();
            final JSONArray transportArray = new JSONArray(getContent());

            for (int transportIndex = 0; transportIndex <= transportArray.length() - 1; transportIndex++) {
                final JSONObject transportObject = transportArray.getJSONObject(transportIndex);
                final String type = transportObject.getString("type");
                final String model = transportObject.getString("model");
                final Transport transport = new Transport(type, model);
                allTransport.add(transport);
            }

            return allTransport;
        } catch (final JSONException exc) {
            throw new DocumentParserException("JSON parsing failed", exc);
        }
    }

    private String getContent() throws DocumentParserException {
        final InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining());
        } catch (final Exception exc) {
            throw new DocumentParserException("Reading file error", exc);
        }
    }
}
