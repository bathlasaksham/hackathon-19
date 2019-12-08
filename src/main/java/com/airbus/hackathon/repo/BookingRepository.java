package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {


}
