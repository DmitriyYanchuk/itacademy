package it.academy.controller;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Validated
public class TestController {
    @GetMapping
    public String test() {
        return "test success";
    }
}
