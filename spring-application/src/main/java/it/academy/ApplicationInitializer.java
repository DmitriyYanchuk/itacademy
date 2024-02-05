package it.academy;

import jakarta.servlet.ServletContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext) {
        final var context = new AnnotationConfigWebApplicationContext();
        context.scan("by.itacademy");
        context.setServletContext(servletContext);

        final var dispatcherServlet = new DispatcherServlet(context);

        var servlet = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
