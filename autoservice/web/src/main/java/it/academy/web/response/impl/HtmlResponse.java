package it.academy.web.response.impl;

import it.academy.web.response.Response;

import java.util.ArrayList;
import java.util.List;

public class HtmlResponse implements Response {

    @Override
    public String response(final List<String> tranposrtList, final boolean isValid, final String columnName) {
        final List<String> table = new ArrayList<>();
        table.add("<table border=\"1\"");
        table.add("<caption>" + columnName + "</caption>");

        table.add("<tr>");
        table.add("<th>Type</th>");
        table.add("<th>Model</th>");
        if (isValid) {
            table.add("<th>Price</th>");
        }

        for (final String transport : tranposrtList) {
            final String[] part = transport.split("\\,\\s");

            table.add("<tr>");
            table.add("<th>" + part[0] + "</th>");
            table.add("<th>" + part[1] + "</th>");
            if (isValid) {
                table.add("<th>" + part[2] + "</th>");
            }

            table.add("</tr>");
        }
        table.add("</tr>");
        table.add("</table>");
        return table.stream().reduce("", String::concat);
    }
}
