package com.carpooling.CarpoolingPlatform.controllers;

import com.carpooling.CarpoolingPlatform.entities.Ride;
import com.carpooling.CarpoolingPlatform.repositories.RideRepository;
import com.carpooling.CarpoolingPlatform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rides")
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new ride
    @PostMapping
    public ResponseEntity<?> createRide(@RequestBody Ride ride) {
        if (ride.getDriver() == null || !userRepository.existsById(ride.getDriver().getId())) {
            return ResponseEntity.badRequest().body("Driver not found or invalid.");
        }

        // Additional validation (example)
        if (ride.getDepartureLocation() == null || ride.getArrivalLocation() == null || ride.getDepartureTime() == null) {
            return ResponseEntity.badRequest().body("Invalid ride data: Missing required fields.");
        }

        Ride savedRide = rideRepository.save(ride);
        return ResponseEntity.ok(savedRide);
    }

    // Get all rides
    @GetMapping
    public ResponseEntity<List<Ride>> getAllRides() {
        List<Ride> rides = rideRepository.findAll();
        if (rides.isEmpty()) {
            return ResponseEntity.noContent().build();  // No content available
        }
        return ResponseEntity.ok(rides);
    }

    // Get a ride by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long id) {
        Optional<Ride> ride = rideRepository.findById(id);
        return ride.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a ride by ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRide(@PathVariable Long id, @RequestBody Ride ride) {
        if (!rideRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (ride.getDriver() == null || !userRepository.existsById(ride.getDriver().getId())) {
            return ResponseEntity.badRequest().body("Driver not found or invalid.");
        }

        // Additional validation (example)
        if (ride.getDepartureLocation() == null || ride.getArrivalLocation() == null || ride.getDepartureTime() == null) {
            return ResponseEntity.badRequest().body("Invalid ride data: Missing required fields.");
        }

        ride.setId(id);
        Ride updatedRide = rideRepository.save(ride);
        return ResponseEntity.ok(updatedRide);
    }

    // Delete a ride by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRide(@PathVariable Long id) {
        if (!rideRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        rideRepository.deleteById(id);
        return ResponseEntity.ok("Ride deleted successfully");
    }
}
