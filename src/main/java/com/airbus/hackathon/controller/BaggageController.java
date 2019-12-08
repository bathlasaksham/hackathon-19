package com.airbus.hackathon.controller;

import com.airbus.hackathon.manager.BaggageManager;
import com.airbus.hackathon.pojo.request.GetBaggageRequest;
import com.airbus.hackathon.pojo.request.UpdateBaggageRequest;
import com.airbus.hackathon.pojo.response.BaggageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public  createBooking(@RequestBody GetBaggageRequest getBaggageRequest) {
        BaggageResponse baggageResponse = new BaggageResponse();
        if (getBaggageRequest.isValid()) {

        } else {
            baggageResponse.setError("Invalid Request");
        }
    }

}
