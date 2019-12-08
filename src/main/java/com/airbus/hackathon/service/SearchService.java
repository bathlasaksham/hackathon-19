package com.airbus.hackathon.service;

import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.manager.BookingManager;
import com.airbus.hackathon.manager.CouponManager;
import com.airbus.hackathon.manager.FlightManager;
import com.airbus.hackathon.manager.RouteManager;
import com.airbus.hackathon.pojo.request.FlightsSearchRequest;
import com.airbus.hackathon.pojo.response.FlightsSearchResponse;
import com.airbus.hackathon.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private BookingManager bookingManager;

    @Autowired
    private RouteManager routeManager;

    @Autowired
    private CouponManager couponManager;

    @Autowired
    private FlightManager flightManager;

    public FlightsSearchResponse getFlightDetails(FlightsSearchRequest flightsSearchRequest) {
        FlightsSearchResponse flightsSearchResponse = new FlightsSearchResponse();
        //TODO
        LocalDate date = DateUtil.toDate(flightsSearchRequest.getDepartureDate());
        String source = flightsSearchRequest.getSource();
        String destination = flightsSearchRequest.getDestination();
        String coupon = flightsSearchRequest.getCoupon();
        Integer noOfPeople = flightsSearchRequest.getNoOfPeople();
        boolean connecting = flightsSearchRequest.isConnecting();
        List<Route> level1Routes = routeManager.getAllRoutes(date, source, destination);
        if (connecting) {
            List<List<Route>> level2Routes = routeManager.getL2Routes(date, source, destination);
        } else {
            if(couponManager.isCouponValid(coupon)) {

            } else {

            }
        }
        return flightsSearchResponse;
    }

}
