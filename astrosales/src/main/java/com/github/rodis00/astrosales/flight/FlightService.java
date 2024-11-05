package com.github.rodis00.astrosales.flight;

import com.github.rodis00.astrosales.flight.exception.FlightNotFoundException;
import com.github.rodis00.astrosales.flight.entity.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found."));
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getAvailableFlights() {
        LocalDateTime today = LocalDateTime.now();
        return flightRepository.getAvailableFlights(today);
    }

    public Flight updateFlight(Integer id, Flight flight) {
        Flight oldFlight = getFlightById(id);
        if (oldFlight != null) {
            oldFlight.setDateOfFlight(flight.getDateOfFlight());
            oldFlight.setStartingPlace(flight.getStartingPlace());
            oldFlight.setLandingPlace(flight.getLandingPlace());
            oldFlight.setTicketPrice(flight.getTicketPrice());
            oldFlight.setTicketPriceVip(flight.getTicketPriceVip());
            oldFlight.setAvailabilityOfPlaces(flight.getAvailabilityOfPlaces());
            flightRepository.save(oldFlight);
        }
        return oldFlight;
    }

    public void deleteFlight(Integer id) {
        Flight flight = getFlightById(id);
        if (flight != null)
            flightRepository.delete(flight);
    }
}
