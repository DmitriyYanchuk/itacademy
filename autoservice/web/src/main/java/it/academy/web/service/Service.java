package it.academy.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Service {

    void startProgram(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
