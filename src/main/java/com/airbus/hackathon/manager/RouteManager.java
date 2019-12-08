package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.repo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RouteManager extends AbstractManager<Route> {

    @Autowired
    private RouteRepo routeRepo;

    @Override
    public JpaRepository<Route, Integer> getRepo() {
        return routeRepo;
    }

    public List<Route> getAllRoutes(LocalDate date, String source, String destination) {
        return routeRepo.getAllRoutes(date, source, destination);
    }

    public List<List<Route>> getL2Routes(LocalDate date, String source, String destination) {
        List<List<Route>> l2Routes = new ArrayList<>();
        List <String> destinations = routeRepo.getAllDestinations(source, destination);
        return l2Routes;
    }
}
