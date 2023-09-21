package it.academy.web.decoder.impl;

import it.academy.web.decoder.Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;

import static by.itacademy.util.Constants.CHARSET;

public class ContentDecoder implements Decoder {

    private final HttpServletRequest request;

    public ContentDecoder(final HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public final String getDecodedContent() throws IOException {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), CHARSET))) {
            final String content = reader.lines().reduce("", String::concat);
            final String jsonContent = content.split("=")[1];

            final String decodedContent = URLDecoder.decode(jsonContent, CHARSET);
            return new ByteArrayInputStream(decodedContent.getBytes(CHARSET)).toString();
        }
    }
}
