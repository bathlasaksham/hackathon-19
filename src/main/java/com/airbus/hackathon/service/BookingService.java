package com.airbus.hackathon.service;

import com.airbus.hackathon.entity.Booking;
import com.airbus.hackathon.manager.BookingManager;
import com.airbus.hackathon.pojo.request.CreateBookingRequest;
import com.airbus.hackathon.pojo.response.BookingDetail;
import com.airbus.hackathon.pojo.response.CreateBookingResponse;
import com.airbus.hackathon.pojo.response.GetBookingsResponse;
import com.airbus.hackathon.util.DateUtil;
import com.airbus.hackathon.util.TransformUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookingService {

    @Autowired
    private BookingManager bookingManager;

    @Autowired
    private AvailabilityService availabilityService;

    public CreateBookingResponse createBooking(CreateBookingRequest createBookingRequest) {
        CreateBookingResponse createBookingResponse = new CreateBookingResponse();
        List<String> errors = new ArrayList<>();
        if (StringUtils.isEmpty(createBookingRequest.getEmailId()) || StringUtils.isEmpty(createBookingRequest.getPhoneNo()) || StringUtils.isEmpty(createBookingRequest.getName())) {
            errors.add("User email, phone no, name mandatory");
            createBookingResponse.setErrors(errors);
            return createBookingResponse;
        }
        boolean isAvailable = true;
        Map<String, Integer> availabilityMap = availabilityService.getAvailability(createBookingRequest.getFlightIds(), createBookingRequest.getDate());
        for (String flightId: createBookingRequest.getFlightIds()) {
            isAvailable = isAvailable && (availabilityMap.getOrDefault(flightId, 0) >= createBookingRequest.getNoOfPeople());
        }
        if (!isAvailable) {
            errors.add("Not enough seats available");
            createBookingResponse.setErrors(errors);
            return createBookingResponse;
        }
        Booking booking = new Booking(DateUtil.toDate(createBookingRequest.getDate()), createBookingRequest.getFlightIds().toString(), createBookingRequest.getSource(),
                createBookingRequest.getDestination(), createBookingRequest.getPrice(), createBookingRequest.getPhoneNo(),
                createBookingRequest.getNoOfPeople(), Booking.Status.CONFIRM);
        booking.setEmail(createBookingRequest.getEmailId());
        if (bookingManager.create(booking) != null) {
            createBookingResponse.setSuccess(true);
        }
        createBookingResponse.setErrors(errors);
        return createBookingResponse;
    }

    public GetBookingsResponse getBookings(String phoneNo) {
        List<Booking> bookings = bookingManager.findByPhone(phoneNo);
        GetBookingsResponse getBookingsResponse = new GetBookingsResponse();
        List<BookingDetail> bookingDetails = new ArrayList<>();
        for (Booking booking: bookings) {
            BookingDetail bookingDetail = new BookingDetail(booking);
            bookingDetails.add(bookingDetail);
        }
        getBookingsResponse.setBookingDetails(bookingDetails);
        return getBookingsResponse;
    }
}
