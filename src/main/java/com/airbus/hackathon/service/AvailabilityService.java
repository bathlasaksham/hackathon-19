package com.airbus.hackathon.service;

import com.airbus.hackathon.entity.Booking;
import com.airbus.hackathon.entity.Flight;
import com.airbus.hackathon.entity.Route;
import com.airbus.hackathon.manager.BookingManager;
import com.airbus.hackathon.manager.FlightManager;
import com.airbus.hackathon.manager.RouteManager;
import com.airbus.hackathon.util.DateUtil;
import com.airbus.hackathon.util.TransformUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AvailabilityService {

    @Autowired
    private BookingManager bookingManager;

    @Autowired
    private FlightManager flightManager;

    @Autowired
    private RouteManager routeManager;

    public Map<String, Integer> getAvailability(List<String> flightIds, String date) {
        Map<String, Integer> flightIdWiseAvailability = new HashMap<>();
        List<Booking> bookings = bookingManager.getBookedSeatsForFlightIdAndDate(date);
        for (String flightId: flightIds) {
            Route route = routeManager.findByFlightId(flightId);
            if (route == null) {
                flightIdWiseAvailability.put(flightId, 0);
                continue;
            }
            Flight flight = flightManager.findByFlightName(flightId);
            if (flight == null) {
                flightIdWiseAvailability.put(flightId, 0);
                continue;
            }
            LocalDate flightDate = DateUtil.toDate(date);
            List<Integer> flightOperationalDaysOfWeek = TransformUtil.toArray(route.getDays());
            if (!flightOperationalDaysOfWeek.contains(flightDate.getDayOfWeek().getValue())) {
                flightIdWiseAvailability.put(flightId, 0);
                continue;
            }
            Integer totalCapacity = flight.getCapacity();
            Integer bookedSeats = 0;
            for (Booking booking: bookings) {
                List<Integer> flightIdsForBooking = TransformUtil.toArray(booking.getFlightIds());
                if (flightIdsForBooking.contains(flightId)) {
                    bookedSeats += booking.getPersons();
                }
            }
            flightIdWiseAvailability.put(flightId, totalCapacity - bookedSeats);
        }
        return flightIdWiseAvailability;
    }

}
