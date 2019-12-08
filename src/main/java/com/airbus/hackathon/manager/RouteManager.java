package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.repo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class RouteManager extends AbstractManager {

    @Autowired
    private RouteRepo routeRepo;

    @Override
    public JpaRepository<Route, Integer> getRepo() {
        return routeRepo;
    }

}
