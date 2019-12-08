package com.airbus.hackathon.service;

import com.airbus.hackathon.manager.BookingManager;
import com.airbus.hackathon.pojo.request.CreateBookingRequest;
import com.airbus.hackathon.pojo.response.CreateBookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingManager bookingManager;

    public CreateBookingResponse createBooking(CreateBookingRequest createBookingRequest) {
        CreateBookingResponse createBookingResponse = new CreateBookingResponse();
        //TODO
        return createBookingResponse;
    }
}
