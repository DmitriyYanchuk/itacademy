package it.academy.web.servlet;

import it.academy.web.service.Service;
import it.academy.web.service.impl.TransportServiceApp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final Service service = new TransportServiceApp();
        service.startProgram(request, response);
    }
}
