package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Booking;
import com.airbus.hackathon.repo.BookingRepository;
import com.airbus.hackathon.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingManager extends AbstractManager<Booking> {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public JpaRepository<Booking, Integer> getRepo() {
        return bookingRepository;
    }

    public List<Booking> getBookedSeatsForFlightIdAndDate(String date) {
        return bookingRepository.getBookedSeatsForFlightIdAndDate(DateUtil.toDate(date).toString());
    }

    public List<Booking> findByPhone(String phoneNo) {
        return bookingRepository.findByPhone(phoneNo);
    }
}
