package com.airbus.hackathon.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingResponse implements Serializable {

    private static final long serialVersionUID = 5040272648644059474L;

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("flights_info")
    private FlightsInfo flightsInfo;

    public CreateBookingResponse() {
    }

    public CreateBookingResponse(boolean success, List<String> errors, FlightsInfo flightsInfo) {
        this.success = success;
        this.errors = errors;
        this.flightsInfo = flightsInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public FlightsInfo getFlightsInfo() {
        return flightsInfo;
    }

    public void setFlightsInfo(FlightsInfo flightsInfo) {
        this.flightsInfo = flightsInfo;
    }

    @Override
    public String toString() {
        return "CreateBookingResponse{" +
                "success=" + success +
                ", errors=" + errors +
                ", flightsInfo=" + flightsInfo +
                '}';
    }

}
