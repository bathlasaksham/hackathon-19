package com.airbus.hackathon.pojo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaggageResponseList implements Serializable {

    private static final long serialVersionUID = 5860272648644059474L;

    @JsonProperty("details")
    private List<BaggageResponse> baggageResponseList;

    public BaggageResponseList() {
    }

    public List<BaggageResponse> getBaggageResponseList() {
        return baggageResponseList;
    }

    public void setBaggageResponseList(List<BaggageResponse> baggageResponseList) {
        this.baggageResponseList = baggageResponseList;
    }

    @Override
    public String toString() {
        return "BaggageResponseList{" +
                "baggageResponseList=" + baggageResponseList +
                '}';
    }

}
