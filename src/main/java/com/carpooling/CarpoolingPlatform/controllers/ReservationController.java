package com.carpooling.CarpoolingPlatform.controllers;

import com.carpooling.CarpoolingPlatform.entities.Reservation;
import com.carpooling.CarpoolingPlatform.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    // Create a new reservation
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(savedReservation);
    }

    // Get all reservations
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }

    // Get a reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        return reservation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a reservation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        if (!reservationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reservation.setId(id);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    // Delete a reservation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        reservationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
