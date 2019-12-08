package com.airbus.hackathon.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightsInfo implements Serializable {

    private static final long serialVersionUID = 1020272648644059474L;

    @JsonProperty("flight_ids")
    private String flightIds;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("end_time")
    private String endTime;

    @JsonProperty("duration")
    private String duration;

    @JsonProperty("price")
    private Map<String, Integer> price;

    @JsonProperty("flight_details")
    private FlightDetails flightDetails;

    public FlightsInfo() {
    }

    public FlightsInfo(String flightIds, String startTime, String endTime, String duration, Map<String, Integer> price, FlightDetails flightDetails) {
        this.flightIds = flightIds;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.price = price;
        this.flightDetails = flightDetails;
    }

    @Override
    public String toString() {
        return "FlightsInfo{" +
                "flightIds='" + flightIds + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", flightDetails=" + flightDetails +
                '}';
    }

}
