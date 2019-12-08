package com.airbus.hackathon.pojo.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateBookingRequest implements Serializable {

    private static final long serialVersionUID = 5070272648644059474L;

    @JsonProperty("flight_ids")
    private List<String> flightIds;

    @JsonProperty("date")
    private String date;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("phone_no")
    private String phoneNo;

    @JsonProperty("name")
    private String name;

    @JsonProperty("no_of_people")
    private String noOfPeople;

    public CreateBookingRequest() {
    }

    public CreateBookingRequest(List<String> flightIds, String date, String emailId, String phoneNo, String name, String noOfPeople) {
        this.flightIds = flightIds;
        this.date = date;
        this.emailId = emailId;
        this.phoneNo = phoneNo;
        this.name = name;
        this.noOfPeople = noOfPeople;
    }

    public List<String> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<String> flightIds) {
        this.flightIds = flightIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    @Override
    public String toString() {
        return "CreateBookingRequest{" +
                "flightIds=" + flightIds +
                ", date='" + date + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", name='" + name + '\'' +
                ", noOfPeople='" + noOfPeople + '\'' +
                '}';
    }

    public boolean isValid() {
        return !StringUtils.isEmpty(emailId) && !StringUtils.isEmpty(phoneNo) && !StringUtils.isEmpty(name) && !CollectionUtils.isEmpty(flightIds) && !StringUtils.isEmpty(noOfPeople) && !StringUtils.isEmpty(date);
    }

}
