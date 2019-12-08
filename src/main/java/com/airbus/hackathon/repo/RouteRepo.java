package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

    @Query(value = "select r from routes r where date = ?1 and source = ?2 and destination = ?3", nativeQuery = true)
    List<Route> getAllRoutes(LocalDate date, String source, String destination);

    @Query(value = "select r from routes r where date = ?1 and source = ?2 and destination = ?3", nativeQuery = true)
    List<String> getAllDestinations(String source, String destination);
}
