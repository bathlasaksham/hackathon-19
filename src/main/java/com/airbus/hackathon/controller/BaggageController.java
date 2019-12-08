package com.airbus.hackathon.controller;

import com.airbus.hackathon.entity.Baggage;
import com.airbus.hackathon.manager.BaggageManager;
import com.airbus.hackathon.pojo.request.GetBaggageRequest;
import com.airbus.hackathon.pojo.request.UpdateBaggageRequest;
import com.airbus.hackathon.pojo.response.BaggageResponse;
import com.airbus.hackathon.pojo.response.BaggageResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/baggage", produces = MediaType.APPLICATION_JSON_VALUE)
public class BaggageController {

    @Autowired
    private BaggageManager baggageManager;

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String createBooking(@RequestBody UpdateBaggageRequest updateBaggageRequest) {
        String success = "false";
        boolean flag = false;
        if (updateBaggageRequest.isValid()) {
            try {
                flag = baggageManager.createOrUpdateBaggage(updateBaggageRequest);
            } catch (Exception e) {
                return success;
            }
        }
        return success;
    }

    @RequestMapping(value = "getDetails", method = RequestMethod.POST)
    public BaggageResponseList createBooking(@RequestBody GetBaggageRequest getBaggageRequest) {
        BaggageResponseList baggageResponseList = new BaggageResponseList();
        List<BaggageResponse> baggages = new ArrayList<>();
        if (getBaggageRequest.isValid()) {
            List<Baggage> baggageList = baggageManager.findByBookingId(getBaggageRequest.getBookingId());
            for (Baggage baggage : baggageList) {
                BaggageResponse baggageResponse = new BaggageResponse();
                if (baggage != null) {
                    baggageResponse.setBookingId(baggage.getBookingId());
                    baggageResponse.setNoOfItems(baggage.getNoOfItems());
                    baggageResponse.setWeight(baggage.getWeight());
                    baggageResponse.setStatus(baggage.getStatus().getName());
                    baggageResponse.setFlightName(baggage.getFlightId());
                } else {
                    baggageResponse.setError("No Baggage Found For Booking Id : " + getBaggageRequest.getBookingId().toString());
                }
                baggages.add(baggageResponse);
            }
        }
        baggageResponseList.setBaggageResponseList(baggages);
        return baggageResponseList;
    }

}
