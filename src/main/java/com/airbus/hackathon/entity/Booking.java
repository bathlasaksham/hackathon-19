package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking extends AbstractEntity<Integer> implements Serializable {

    private LocalDate date;

    private String flightIds;

    private String source;

    private String destination;

    private Integer amount;

    private String phone;

    private Integer persons;

    public Booking() {
    }

    public Booking(LocalDate date, String flightIds, String source, String destination, Integer amount, String phone, Integer persons) {
        this.date = date;
        this.flightIds = flightIds;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.phone = phone;
        this.persons = persons;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "date=" + date +
                ", flightIds='" + flightIds + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", amount=" + amount +
                ", phone='" + phone + '\'' +
                ", persons=" + persons +
                '}';
    }

}
