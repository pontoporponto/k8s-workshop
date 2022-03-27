package com.mindera.workshop.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/workshop")
public class BasicController {
    private boolean shutdown = Boolean.FALSE;

    @GetMapping("/{team}/simple")
    public ResponseEntity<String> basicEndpoint(@PathVariable("team") String team) throws UnknownHostException {
        StringBuilder contents = new StringBuilder();
        contents.append("Hello World -> ")
                .append(Inet4Address.getLocalHost().getHostName())
                .append("\n")
                .append("Well done team workshop-")
                .append(team)
                .append("!\n");

        return shutdown ? ResponseEntity.notFound().build() :
                ResponseEntity.ok(contents.toString());
    }

    @GetMapping("/shutdown")
    public String shutdown() throws UnknownHostException {
        shutdown = Boolean.TRUE;
        return "Shutting down....";
    }
}
