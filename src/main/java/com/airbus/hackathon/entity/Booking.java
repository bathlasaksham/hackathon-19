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

    private String email;

    private Integer amount;

    private String phone;

    private Integer persons;

    private Booking.Status status;

    public enum Status {

        CONFIRM("Confirm Booking"), CHECKIN("Checked In"), CHECKOUT("Checked Out"), CANCELLED("Cancelled Booking"),
        NO_SHOW("No Show"), SAVED("Saved");

        private String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static final Booking.Status BOOKING_STATUS_ARRAY[];

        static {
            BOOKING_STATUS_ARRAY = Booking.Status.values();
        }
    }

    public Booking() {
    }

    public Booking(LocalDate date, String flightIds, String source, String destination, Integer amount, String phone, Integer persons, Status status) {
        this.date = date;
        this.flightIds = flightIds;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.phone = phone;
        this.persons = persons;
        this.status = status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
