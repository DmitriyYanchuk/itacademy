package yanchuk.autodiagnosticcenter.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonDocumentParser implements DocumentParser {

    private final String content;

    public JsonDocumentParser(final String name) {
        this.content = name;
    }

    @Override
    public final List<String> parse() throws DocumentParserException {
        try {
            final List<String> allTransport = new ArrayList<>();
            final JSONArray transportArray = new JSONArray(getContent());

            for (int transportIndex = 0; transportIndex <= transportArray.length() - 1; transportIndex++) {
                final JSONObject transportObject = transportArray.getJSONObject(transportIndex);
                final String type = transportObject.getString("type");
                final String model = transportObject.getString("model");
                final String transport = type + ", " + model;
                allTransport.add(transport);
            }

            return allTransport;
        } catch (final JSONException exc) {
            throw new DocumentParserException("JSON parsing failed", exc);
        }
    }

    private String getContent() throws DocumentParserException {
        final InputStream in = getClass().getClassLoader().getResourceAsStream(content);
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining());
        } catch (final Exception exc) {
            throw new DocumentParserException("Reading file error", exc);
        }
    }
}