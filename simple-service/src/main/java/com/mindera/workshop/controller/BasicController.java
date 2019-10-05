package com.mindera.workshop.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workshop")
public class BasicController {

    @GetMapping("/simple")
    public String basicEndpoint() {
        return "Hello World";
    }
}
