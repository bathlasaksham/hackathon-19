package com.airbus.hackathon.controller;

import com.airbus.hackathon.pojo.request.FlightsSearchRequest;
import com.airbus.hackathon.pojo.response.FlightsSearchResponse;
import com.airbus.hackathon.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/flights", produces = MediaType.APPLICATION_JSON_VALUE)
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public FlightsSearchResponse getFlightDetails(@RequestBody FlightsSearchRequest flightsSearchRequest) {
        FlightsSearchResponse flightsSearchResponse = new FlightsSearchResponse();
        flightsSearchResponse = searchService.getFlightDetails(flightsSearchRequest);
        return flightsSearchResponse;
    }

}
