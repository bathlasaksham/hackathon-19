package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.repo.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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

    public List<Route> getAllRoutes( String source, String destination) {
        return routeRepo.getAllRoutes(source, destination);
    }

    public List<List<Route>> getL2Routes(LocalDate date, String source, String destination) {
        List<List<Route>> l2Routes = new ArrayList<>();
        List <String> stops = routeRepo.getAllDestinations(source, destination);
        if (!CollectionUtils.isEmpty(stops)) {
            for (int i = 0; i< stops.size(); i++) {
                List<Route> sroutes = routeRepo.getAllRoutes(source, stops.get(i));
                List<Route> eroutes = routeRepo.getAllRoutes(stops.get(i), destination);
                for (int j=0; j< sroutes.size(); j++) {
                    for (int k=0; k< eroutes.size(); k++) {
                        List<Route> routeList = new ArrayList<>();
                        routeList.add(sroutes.get(j));
                        routeList.add(eroutes.get(k));
                        l2Routes.add(routeList);
                    }
                }
            }
        }
        return l2Routes;
    }

    public Route findByFlightId(String flightId) {
        return routeRepo.findByFlightId(flightId);
    }

}
