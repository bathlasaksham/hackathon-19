package com.airbus.hackathon.pojo.response;

import com.airbus.hackathon.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBookingsResponse implements Serializable {

    private static final long serialVersionUID = 1020272610244059474L;

    @JsonProperty("transactions")
    private List<BookingDetail> bookingDetails;



    public GetBookingsResponse() {
    }

    public GetBookingsResponse(String date, String flightIds, String source, String destination, Integer amount, Integer persons, Booking.Status status) {
        this.date = date;
        this.flightIds = flightIds;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.persons = persons;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(String flightIds) {
        this.flightIds = flightIds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    public Booking.Status getStatus() {
        return status;
    }

    public void setStatus(Booking.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetBookingsResponse{" +
                "date='" + date + '\'' +
                ", flightIds='" + flightIds + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", amount=" + amount +
                ", persons=" + persons +
                ", status=" + status +
                '}';
    }

}
