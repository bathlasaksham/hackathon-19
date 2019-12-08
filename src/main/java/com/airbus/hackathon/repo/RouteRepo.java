package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

    Route findByFlightId(String flightId);
}
