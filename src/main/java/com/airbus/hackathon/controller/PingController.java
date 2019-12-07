package com.airbus.hackathon.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
public class PingController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String healthPing() {
        return "success";
    }

}
