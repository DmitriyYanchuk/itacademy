package it.academy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@ComponentScan(basePackages = "by.itacademy")
public class WebConfigurer implements WebMvcConfigurer {

    private final LocalValidatorFactoryBean validatorFactoryBean;

    private final ObjectMapper objectMapper;

    @Autowired
    public WebConfigurer(LocalValidatorFactoryBean validatorFactoryBean, ObjectMapper objectMapper) {
        this.validatorFactoryBean = validatorFactoryBean;
        this.objectMapper = objectMapper;
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
        converters.add(new ObjectToStringHttpMessageConverter(new DefaultConversionService()));
    }

    @Override
    public Validator getValidator() {
        return validatorFactoryBean;
    }
}
