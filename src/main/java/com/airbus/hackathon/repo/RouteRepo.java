package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

    @Query(value = "select * from routes r where source = (:source) and destination = (:destination)", nativeQuery = true)
    List<Route> getAllRoutes(@Param("source") String source, @Param("destination") String destination);

    @Query(value = "select r.destination from routes r, routes r2 where r.id <> r2.id and r.source = ?1 and r.destination = r2.source and r2.destination = ?2", nativeQuery = true)
    List<String> getAllDestinations(String source, String destination);

    Route findByFlightId(String flightId);

}
