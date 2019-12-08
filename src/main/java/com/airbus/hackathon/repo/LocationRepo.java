package com.airbus.hackathon.repo;

import com.airbus.hackathon.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<Location, Integer> {



}
