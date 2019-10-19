package com.mindera.workshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BasicController {
    private final RestTemplate client;
    private boolean shutdown = Boolean.FALSE;

    @Autowired
    public BasicController() {
        this.client = new RestTemplate();
    }

    @GetMapping("/client")
    public ResponseEntity<String> basicEndpoint() {
        ResponseEntity<String> response = client.getForEntity("http://service-endpoint:80/workshop/simple", String.class);

        String content;
        if (response.getStatusCode().is2xxSuccessful()) {
            content = response.getBody();
        } else {
            content = "Wrong response! Err[" + response.getStatusCode().toString() + "]";
        }


        return shutdown ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(content);
    }

    @GetMapping("/shutdown")
    public String shutdown() {
        shutdown = Boolean.TRUE;
        return "Shutting down....";
    }
}
