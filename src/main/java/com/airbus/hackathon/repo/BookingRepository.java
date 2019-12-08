package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "select count(*) from bookings", nativeQuery = true)
    Integer getBookedSeatsForFlightIdAndDate(String flightId, String date);

}
