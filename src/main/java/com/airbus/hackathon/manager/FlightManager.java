package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Flight;
import com.airbus.hackathon.repo.FlightRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class FlightManager extends AbstractManager {

    @Autowired
    private FlightRepo flightRepo;

    @Override
    public JpaRepository<Flight, Integer> getRepo() {
        return flightRepo;
    }
}
