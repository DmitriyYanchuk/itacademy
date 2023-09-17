package it.academy.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ApplicationServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String servletParamValue = getServletConfig().getInitParameter("servlet-param");

        response.setContentType("text/html");
        try (final PrintWriter writer = response.getWriter()) {
            writer.println("<h1>Hello ApplicationServlet</h1>");
            writer.println("<h2>" + servletParamValue + "</h2>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String firstParamValue = getServletContext().getInitParameter("first-param");
        final String secondParamValue = getServletContext().getInitParameter("second-param");

        response.setContentType("text/html");
        try (final PrintWriter writer = response.getWriter()) {
            writer.println("<h2>" + firstParamValue + "</h2>");
            writer.println("<h2>" + secondParamValue + "</h2>");
        }
    }
}
