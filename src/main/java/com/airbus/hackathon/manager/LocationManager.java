package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Location;
import com.airbus.hackathon.repo.LocationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LocationManager extends AbstractManager<Location> {

    @Autowired
    private LocationRepo locationRepo;

    @Override
    public JpaRepository<Location, Integer> getRepo() {
        return locationRepo;
    }


    public List<Location> getLocations() {
        return findAll();
    }
}
