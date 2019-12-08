package com.airbus.hackathon.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightsSearchResponse implements Serializable {

    private static final long serialVersionUID = 5060272648644059474L;

    @JsonProperty("source_to_destination")
    private List<FlightsInfo> sourceToDestination;

    public FlightsSearchResponse() {
    }

    public FlightsSearchResponse(List<FlightsInfo> sourceToDestination) {
        this.sourceToDestination = sourceToDestination;
    }

    public List<FlightsInfo> getSourceToDestination() {
        return sourceToDestination;
    }

    public void setSourceToDestination(List<FlightsInfo> sourceToDestination) {
        this.sourceToDestination = sourceToDestination;
    }

    @Override
    public String toString() {
        return "FlightsSearchResponse{" +
                "sourceToDestination=" + sourceToDestination +
                '}';
    }

}
