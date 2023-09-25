package by.itacademy.parser.impl;

import by.itacademy.parser.DocumentParser;
import by.itacademy.parser.DocumentParserException;
import by.itacademy.transport.Transport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonDocumentParser implements DocumentParser {

    @Override
    public final List<Transport> parse(final String content) throws DocumentParserException {
        try {
            final List<Transport> allTransport = new ArrayList<>();
            final JSONArray transportArray = new JSONArray(content);

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
}
