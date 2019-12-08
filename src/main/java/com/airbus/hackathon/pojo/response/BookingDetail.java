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

    public BookingDetail() {
    }

    public BookingDetail(Booking booking) {
        this.date = booking.getDate().toString();
        this.flightIds = booking.getFlightIds();
        this.source = booking.getSource();
        this.destination = booking.getDestination();
        this.amount = booking.getAmount();
        this.persons = booking.getPersons();
        this.status = booking.getStatus();
    }

    public BookingDetail(String date, String flightIds, String source, String destination, Integer amount, Integer persons, Booking.Status status) {
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
        return "BookingDetail{" +
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
