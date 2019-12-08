package com.airbus.hackathon.service;

import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.manager.BookingManager;
import com.airbus.hackathon.manager.CouponManager;
import com.airbus.hackathon.manager.FlightManager;
import com.airbus.hackathon.manager.RouteManager;
import com.airbus.hackathon.pojo.request.FlightsSearchRequest;
import com.airbus.hackathon.pojo.response.FlightDetails;
import com.airbus.hackathon.pojo.response.FlightsInfo;
import com.airbus.hackathon.pojo.response.FlightsSearchResponse;
import com.airbus.hackathon.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AvailabilityService availabilityService;

    public FlightsSearchResponse getFlightDetails(FlightsSearchRequest flightsSearchRequest) {
        FlightsSearchResponse flightsSearchResponse = new FlightsSearchResponse();
        String date = flightsSearchRequest.getDepartureDate();
        LocalDate localDate = DateUtil.toDate(date);
        String source = flightsSearchRequest.getSource();
        String destination = flightsSearchRequest.getDestination();
        String coupon = flightsSearchRequest.getCoupon();
        Integer noOfPeople = flightsSearchRequest.getNoOfPeople();
        boolean connecting = flightsSearchRequest.isConnecting();
        List<Route> level1Routes = routeManager.getAllRoutes(source, destination);
        List<List<Route>> level2Routes = new ArrayList<>();
        List<FlightsInfo> direct = new ArrayList<>();
        level1Routes = filterAvailableRoutes(level1Routes, date, noOfPeople);
        List<FlightsInfo> conFlights = new ArrayList<>();
        if (connecting) {
            level2Routes = routeManager.getL2Routes(localDate, source, destination);
            level2Routes = filterAvailableL2Routes(level2Routes, date, noOfPeople);
            conFlights = createL2Response(level2Routes, flightsSearchRequest);
        }

        direct = createResponse(level1Routes, flightsSearchRequest);
        if (!CollectionUtils.isEmpty(conFlights)) {
            direct.addAll(conFlights);
        }
        flightsSearchResponse.setSourceToDestination(direct);
        return flightsSearchResponse;
    }

    private List<FlightsInfo> createResponse(List<Route> level1Routes, FlightsSearchRequest flightsSearchRequest) {
        List<FlightsInfo> flightsInfoList = new ArrayList<>();
        for (Route route: level1Routes) {
            FlightsInfo flightsInfo = new FlightsInfo();
            flightsInfo.setStartTime(route.getStartTime());
            flightsInfo.setEndTime(route.getEndTime());
            flightsInfo.setFlightIds(route.getFlightId());
            flightsInfo.setPrice(route.getBasePrice());
            FlightDetails flightDetails = new FlightDetails();
            flightDetails.setEndTime(route.getEndTime());
            flightDetails.setStartTime(route.getStartTime());
            flightDetails.setFlightId(route.getFlightId());
            flightsInfo.setDuration(getTimeString(Integer.valueOf(route.getEndTime()) - Integer.valueOf(route.getStartTime())));
            List<FlightDetails> flightDetailsList = new ArrayList<>();
            flightDetailsList.add(flightDetails);
            flightsInfo.setFlightDetails(flightDetailsList);
            flightsInfoList.add(flightsInfo);
        }
        return flightsInfoList;
    }

    private List<FlightsInfo> createL2Response(List<List<Route>> level2Routes, FlightsSearchRequest flightsSearchRequest) {
        List<FlightsInfo> flightsInfoList = new ArrayList<>();
        for (List<Route> routeList: level2Routes) {
            FlightsInfo flightsInfo = new FlightsInfo();
            flightsInfo.setStartTime(routeList.get(0).getStartTime());
            flightsInfo.setEndTime(routeList.get(1).getEndTime());
            flightsInfo.setFlightIds(routeList.get(0).getFlightId() + "," + routeList.get(1).getFlightId());
            flightsInfo.setPrice(routeList.get(0).getBasePrice() + routeList.get(1).getBasePrice());
            flightsInfo.setDuration(getTimeString(Integer.valueOf(routeList.get(1).getEndTime()) - Integer.valueOf(routeList.get(0).getStartTime())));
            FlightDetails flightDetails = new FlightDetails();
            FlightDetails flightDetails2 = new FlightDetails();
            flightDetails.setEndTime(routeList.get(0).getEndTime());
            flightDetails.setStartTime(routeList.get(0).getStartTime());
            flightDetails.setFlightId(routeList.get(0).getFlightId());
            flightDetails2.setEndTime(routeList.get(1).getEndTime());
            flightDetails2.setStartTime(routeList.get(1).getStartTime());
            flightDetails2.setFlightId(routeList.get(1).getFlightId());
            List<FlightDetails> flightDetailsList = new ArrayList<>();
            flightDetailsList.add(flightDetails2);
            flightDetailsList.add(flightDetails);
            flightsInfo.setFlightDetails(flightDetailsList);
            flightsInfo.setStop(routeList.get(1).getSource());
            flightsInfoList.add(flightsInfo);
        }
        return flightsInfoList;
    }

    private String getTimeString(int i) {
        int minutes = i%100;
        int hours = (i - i%100)/100;
        return (String.valueOf(hours) + " hours " + String.valueOf(minutes) + " minutes");
    }

    public List<List<Route>> filterAvailableL2Routes(List<List<Route>> routeList, String date, Integer noOfPeople) {
        List<Integer> removeIds = new ArrayList<>();
        int i=0;
        for (List<Route> routeList1: routeList) {
            List<Route> filteredList = filterAvailableRoutes(routeList1, date, noOfPeople);
            if (filteredList.size() < 2) {
                removeIds.add(i);
            }
            i++;
        }
        for (int j=0; j< removeIds.size(); j++) {
            routeList.remove(j);
        }
        return routeList;
    }

    public List<Route> filterAvailableRoutes(List<Route> routes, String date, Integer noOfPeople) {
        List<Route> routeList = new ArrayList<>();
        List<String> flightIds = new ArrayList<>();
        Map<String, Route> map = new HashMap<>();
        for (int i=0; i<routes.size(); i++) {
            flightIds.add(routes.get(i).getFlightId());
            map.put(routes.get(i).getFlightId(), routes.get(i));
        }
        Map<String, Integer> availability = availabilityService.getAvailability(flightIds, date);
        for (String flightId: availability.keySet()) {
            if (availability.get(flightId) < noOfPeople) {
                map.remove(flightId);
            }
        }
        for (String flightId: map.keySet()) {
            routeList.add(map.get(flightId));
        }
        return routeList;
    }

}
