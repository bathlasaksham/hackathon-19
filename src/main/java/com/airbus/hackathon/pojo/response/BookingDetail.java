package com.airbus.hackathon.pojo.response;

import com.airbus.hackathon.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDetail implements Serializable {

    @JsonProperty("date")
    private String date;

    @JsonProperty("flight_ids")
    private String flightIds;

    @JsonProperty("source")
    private String source;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("amount")
    private Integer amount;

    @JsonProperty("persons")
    private Integer persons;

    @JsonProperty("status")
    private Booking.Status status;

}
