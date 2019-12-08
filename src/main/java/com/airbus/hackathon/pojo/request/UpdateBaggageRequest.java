package com.airbus.hackathon.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateBaggageRequest implements Serializable {

    private static final long serialVersionUID = 9070272648644059474L;

    @JsonProperty("booking_id")
    private Integer bookingId;

    @JsonProperty("flight_id")
    private String flightId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("weight")
    private Float weight;

    @JsonProperty("no_of_items")
    private Integer noOfItems;

    public UpdateBaggageRequest() {
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    @JsonIgnore
    public boolean isValid() {
        return (bookingId != null && flightId != null && !StringUtils.isEmpty(status) && weight != null && noOfItems != null);
    }

    @Override
    public String toString() {
        return "UpdateBaggageRequest{" +
                "bookingId=" + bookingId +
                ", status=" + status +
                ", weight=" + weight +
                ", noOfItems=" + noOfItems +
                ", flightId=" + flightId +
                '}';
    }

}
