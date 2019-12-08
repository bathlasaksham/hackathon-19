package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "baggages")
public class Baggage extends AbstractEntity<Integer> implements Serializable {

    private Integer bookingId;

    private Float weight;

    private Integer noOfItems;

    private Status status;

    private String flightId;

    public enum Status {

        NOT_CHECKED_IN("Not Checked In"), CHECKED_IN("Checked In"), IN_TRANSIT("In Transit"), X_RAY("X Ray"),
        CARGO_LOADING("Cargo Loading"), LOST("Lost");

        private String name;

        Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static final Baggage.Status BAGGAGE_STATUS_ARRAY[];

        static {
            BAGGAGE_STATUS_ARRAY = Baggage.Status.values();
        }
    }

    public Baggage() {
    }

    public Baggage(Integer bookingId, Float weight, Integer noOfItems, Status status) {
        this.bookingId = bookingId;
        this.weight = weight;
        this.noOfItems = noOfItems;
        this.status = status;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "bookingId=" + bookingId +
                ", weight=" + weight +
                ", noOfItems=" + noOfItems +
                ", status=" + status +
                ", flightId=" + flightId +
                '}';
    }

}
