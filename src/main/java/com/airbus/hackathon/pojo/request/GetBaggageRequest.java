package com.airbus.hackathon.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBaggageRequest implements Serializable {

    private static final long serialVersionUID = 6070272648644059474L;

    @JsonProperty("booking_id")
    private Integer bookingId;

    public GetBaggageRequest() {
    }

    public GetBaggageRequest(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    @JsonIgnore
    public boolean isValid() {
        return (bookingId != null);
    }

    @Override
    public String toString() {
        return "GetBaggageRequest{" +
                "bookingId=" + bookingId +
                '}';
    }

}
