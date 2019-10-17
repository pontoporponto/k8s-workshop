package com.mindera.workshop.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/workshop")
public class BasicController {
    private boolean shutdown = Boolean.FALSE;

    @GetMapping("/simple")
    public ResponseEntity<String> basicEndpoint() throws UnknownHostException {
        return shutdown ? ResponseEntity.notFound().build() :
                ResponseEntity.ok("Hello World -> " + Inet4Address.getLocalHost().getHostName());
    }

    @GetMapping("/shutdown")
    public String shutdown() throws UnknownHostException {
        shutdown = Boolean.TRUE;
        return "Shutting down....";
    }
}
