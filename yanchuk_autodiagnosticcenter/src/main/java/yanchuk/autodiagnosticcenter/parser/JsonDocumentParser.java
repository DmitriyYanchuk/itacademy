package yanchuk.autodiagnosticcenter.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class JsonDocumentParser implements DocumentParser {

    private final File content;

    public JsonDocumentParser(String name) {
        this.content = new File(name);
    }

    @Override
    public List<String> parse() throws DocumentParserException {
        try {
            final List<String> allTransport = new ArrayList<>();
            final String documentStrings = Files.readString(content.toPath());
            final JSONArray transportArray = new JSONArray(documentStrings);

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
        } catch (final IOException exc) {
            throw new DocumentParserException("File " + content + " not found", exc);
        }
    }
}
