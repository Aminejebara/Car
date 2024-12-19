package com.carpooling.CarpoolingPlatform.repositories;

import com.carpooling.CarpoolingPlatform.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
