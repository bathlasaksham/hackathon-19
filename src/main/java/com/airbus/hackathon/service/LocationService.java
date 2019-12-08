package com.airbus.hackathon.service;

import com.airbus.hackathon.manager.LocationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationManager locationManager;

    public List<String> getLocations() {
        return new ArrayList<String>() {
            {
                add("Delhi");add("Mumbai");add("Hyderabad");add("Chennai");add("Bengaluru");add("Kolkata");add("Guwahati");
            }
        };
        //TODO
//        return locationsManager.getLocations();
    }

}
