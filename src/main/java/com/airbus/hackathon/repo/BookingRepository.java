package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "select * from bookings b  where b.date = ?1 and status in (0,1,2,5)", nativeQuery = true)
    List<Booking> getBookedSeatsForFlightIdAndDate(@Param("date") String date);

    @Query(value = "select * from bookings b where b.phone = :phone_no order by b.updated_at desc", nativeQuery = true)
    List<Booking> findByPhone(@Param("phone_no") String phoneNo);
}
