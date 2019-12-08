package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaggageRepo extends JpaRepository<Baggage, Integer> {

    List<Baggage> findByBookingId(Integer bookingId);

    Baggage findByBookingIdAndFlightId(Integer bookingId, String flightId);

}
