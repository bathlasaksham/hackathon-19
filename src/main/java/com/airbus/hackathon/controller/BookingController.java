package com.airbus.hackathon.controller;

import com.airbus.hackathon.pojo.request.CreateBookingRequest;
import com.airbus.hackathon.pojo.response.CreateBookingResponse;
import com.airbus.hackathon.pojo.response.GetBookingsResponse;
import com.airbus.hackathon.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public CreateBookingResponse createBooking(@RequestBody CreateBookingRequest createBookingRequest) throws Exception {
        CreateBookingResponse createBookingResponse = new CreateBookingResponse();
        createBookingResponse = bookingService.createBooking(createBookingRequest);
        return createBookingResponse;
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public GetBookingsResponse createBooking(@RequestParam String phoneNo) throws Exception {
        GetBookingsResponse getBookingsResponse = bookingService.getBookings(phoneNo);
        return getBookingsResponse;
    }

}
