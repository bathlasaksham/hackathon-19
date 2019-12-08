package com.airbus.hackathon.controller;

import com.airbus.hackathon.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocationController {

    @Autowired
    private LocationService locationService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<String> getLocations() {
        return locationService.getLocations();
    }
}
