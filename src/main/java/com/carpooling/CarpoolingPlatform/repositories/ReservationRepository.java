package com.carpooling.CarpoolingPlatform.repositories;

import com.carpooling.CarpoolingPlatform.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
