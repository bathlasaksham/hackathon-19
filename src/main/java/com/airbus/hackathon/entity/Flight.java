package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "flights")
public class Flight extends AbstractEntity<Integer> implements Serializable {

    private String airline;

    private String flightName;

    private Integer capacity;

    public Flight() {
    }

    public Flight(String airline, String flightName, Integer capacity) {
        this.airline = airline;
        this.flightName = flightName;
        this.capacity = capacity;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "airline='" + airline + '\'' +
                ", flightName='" + flightName + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
