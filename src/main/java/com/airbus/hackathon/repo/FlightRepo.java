package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends JpaRepository<Flight, Integer> {

    Flight findByFlightName(String flightId);
}
