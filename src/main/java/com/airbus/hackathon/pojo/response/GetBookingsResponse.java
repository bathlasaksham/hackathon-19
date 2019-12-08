package com.airbus.hackathon.pojo.response;

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

    public GetBookingsResponse(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public List<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(List<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    @Override
    public String toString() {
        return "GetBookingsResponse{" +
                "bookingDetails=" + bookingDetails +
                '}';
    }

}
