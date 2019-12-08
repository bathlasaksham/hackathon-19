package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "routes")
public class Route extends AbstractEntity<Integer> implements Serializable {

    private String flightId;

    private String source;

    private String destination;

    private String startTime;

    private String endTime;

    private String days;

    public Route() {
    }

    public Route(String flightId, String source, String destination, String startTime, String endTime, String days) {
        this.flightId = flightId;
        this.source = source;
        this.destination = destination;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
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

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Route{" +
                "flightId='" + flightId + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", days='" + days + '\'' +
                '}';
    }

}
