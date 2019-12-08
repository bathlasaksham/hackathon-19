package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Booking;
import com.airbus.hackathon.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingManager extends AbstractManager<Booking> {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public JpaRepository<Booking, Integer> getRepo() {
        return bookingRepository;
    }
}
