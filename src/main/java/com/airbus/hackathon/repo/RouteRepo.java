package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepo extends JpaRepository<Route, Integer> {
}
