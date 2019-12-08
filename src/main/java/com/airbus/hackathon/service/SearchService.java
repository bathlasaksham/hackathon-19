package com.airbus.hackathon.service;

import com.airbus.hackathon.pojo.request.FlightsSearchRequest;
import com.airbus.hackathon.pojo.response.FlightsSearchResponse;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    public FlightsSearchResponse getFlightDetails(FlightsSearchRequest flightsSearchRequest) {
        FlightsSearchResponse flightsSearchResponse = new FlightsSearchResponse();
        //TODO
        return flightsSearchResponse;
    }

}
