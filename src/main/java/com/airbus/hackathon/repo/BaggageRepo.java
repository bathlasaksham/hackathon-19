package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepo extends JpaRepository<Baggage, Integer> {

    Baggage findByBookingId(Integer bookingId);

}
