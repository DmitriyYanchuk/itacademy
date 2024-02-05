package it.academy.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "by.itacademy")
@PropertySource({"classpath:application.properties", "classpath:logback.xml"})
public class BeanConfiguration {


}
