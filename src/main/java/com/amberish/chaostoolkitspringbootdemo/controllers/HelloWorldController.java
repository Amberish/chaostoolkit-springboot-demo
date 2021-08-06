package com.amberish.chaostoolkitspringbootdemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String sayHelloWorld() {
        return "Hello World";
    }
}
