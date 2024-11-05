package com.github.rodis00.astrosales.flight;

import com.github.rodis00.astrosales.flight.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("astrosales/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightService.saveFlight(flight));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getAllFlights());
    }

    @GetMapping("")
    public ResponseEntity<List<Flight>> getAvailableFlights() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getAvailableFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.getFlightById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFlight(@PathVariable Integer id, @RequestBody Flight flight) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flightService.updateFlight(id, flight));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Integer id) {
        flightService.deleteFlight(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
