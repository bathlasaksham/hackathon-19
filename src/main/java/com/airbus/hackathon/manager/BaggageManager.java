package com.airbus.hackathon.manager;

import com.airbus.hackathon.entity.Baggage;
import com.airbus.hackathon.pojo.request.UpdateBaggageRequest;
import com.airbus.hackathon.repo.BaggageRepo;
import com.newrelic.api.agent.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

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
        Baggage existing = baggageRepo.findByBookingId(updateBaggageRequest.getBookingId());
        Baggage baggage = new Baggage();
        baggage.setBookingId(updateBaggageRequest.getBookingId());
        baggage.setNoOfItems(updateBaggageRequest.getNoOfItems());
        if (getStatusByName(updateBaggageRequest.getStatus()) == null) {
            return false;
        }
        baggage.setStatus(getStatusByName(updateBaggageRequest.getStatus()));
        baggage.setWeight(updateBaggageRequest.getWeight());
        try {
            if (existing == null) {
                try {
                    baggageRepo.save(baggage);
                    return true;
                } catch (org.hibernate.exception.ConstraintViolationException | org.springframework.dao.DataIntegrityViolationException | org.hibernate.exception.GenericJDBCException | org.hibernate.exception.DataException e) {
                    existing = baggageRepo.findByBookingId(baggage.getBookingId());
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
    }

    public Baggage findByBookingId(Baggage baggage) {
        
    }

}
