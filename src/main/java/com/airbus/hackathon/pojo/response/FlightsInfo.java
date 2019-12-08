package com.airbus.hackathon.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
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
    private Integer price;

    @JsonProperty("flight_details")
    private List<FlightDetails> flightDetails;

    public FlightsInfo() {
    }

    public FlightsInfo(String flightIds, String startTime, String endTime, String duration, Integer price, List<FlightDetails> flightDetails) {
        this.flightIds = flightIds;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.price = price;
        this.flightDetails = flightDetails;
    }

    public String getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(String flightIds) {
        this.flightIds = flightIds;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<FlightDetails> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightDetails> flightDetails) {
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
