package com.airbus.hackathon.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "locations")
public class Location extends AbstractEntity<Integer> implements Serializable {

    private Integer locationId;

    private String locationName;

    private String airportName;

    private String country;

    public Location() {
    }

    public Location(Integer locationId, String locationName, String airportName, String country) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.airportName = airportName;
        this.country = country;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", airportName='" + airportName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
