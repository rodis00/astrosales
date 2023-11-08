package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.exception.FlightNotFoundException;
import com.github.rodis00.astrosales.model.Flight;
import com.github.rodis00.astrosales.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.saveFlight(flight));
    }

    @GetMapping("")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(flightService.getFlightById(id));
        } catch (FlightNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFligt(@PathVariable Integer id, @RequestBody Flight flight) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(flightService.updateFlight(id, flight));
        } catch (FlightNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(flightService.deleteFlight(id));
        } catch (FlightNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }
}
