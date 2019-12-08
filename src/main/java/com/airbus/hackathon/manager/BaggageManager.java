package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Baggage;
import com.airbus.hackathon.pojo.request.UpdateBaggageRequest;
import com.airbus.hackathon.repo.BaggageRepo;
import com.newrelic.api.agent.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaggageManager extends AbstractManager {

    @Autowired
    private BaggageRepo baggageRepo;

    @Override
    public JpaRepository<Baggage, Integer> getRepo() {
        return baggageRepo;
    }

    @Trace
    public Baggage.Status getStatusByName(String name) throws Exception {
        for (Baggage.Status st : Baggage.Status.BAGGAGE_STATUS_ARRAY) {
            if (st.getName().equalsIgnoreCase(name)) {
                return st;
            }
        }
        return null;
    }

    @Trace
    public boolean createOrUpdateBaggage(UpdateBaggageRequest updateBaggageRequest) throws Exception {
        Baggage existing = baggageRepo.findByBookingIdAndFlightId(updateBaggageRequest.getBookingId(), updateBaggageRequest.getFlightId());
        Baggage baggage = new Baggage();
        baggage.setBookingId(updateBaggageRequest.getBookingId());
        baggage.setNoOfItems(updateBaggageRequest.getNoOfItems());
        if (getStatusByName(updateBaggageRequest.getStatus()) == null) {
            return false;
        }
        baggage.setStatus(getStatusByName(updateBaggageRequest.getStatus()));
        baggage.setWeight(updateBaggageRequest.getWeight());
        baggage.setFlightId(updateBaggageRequest.getFlightId());
        try {
            if (existing == null) {
                try {
                    baggageRepo.save(baggage);
                    return true;
                } catch (org.hibernate.exception.ConstraintViolationException | org.springframework.dao.DataIntegrityViolationException | org.hibernate.exception.GenericJDBCException | org.hibernate.exception.DataException e) {
                    existing = baggageRepo.findByBookingIdAndFlightId(baggage.getBookingId(), baggage.getFlightId());
                    if (existing == null) {
                        baggageRepo.save(baggage);
                        return true;
                    } else {
                        updateBaggage(baggage, existing);
                        baggageRepo.save(existing);
                        return true;
                    }
                } catch (Exception ex) {
                    return true;
                }
            } else {
                updateBaggage(baggage, existing);
                baggageRepo.save(existing);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private void updateBaggage(Baggage newBaggage, Baggage existing) {
        existing.setBookingId(newBaggage.getBookingId());
        existing.setWeight(newBaggage.getWeight());
        existing.setNoOfItems(newBaggage.getNoOfItems());
        existing.setStatus(newBaggage.getStatus());
        existing.setFlightId(newBaggage.getFlightId());
    }

    public List<Baggage> findByBookingId(Integer bookingId) {
       return baggageRepo.findByBookingId(bookingId);
    }

    public Baggage findByBookingIdAndFlightId(Integer bookingId, String flightId) {
        return baggageRepo.findByBookingIdAndFlightId(bookingId, flightId);
    }

}
