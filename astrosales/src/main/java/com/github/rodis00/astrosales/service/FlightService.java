package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.FlightNotFoundException;
import com.github.rodis00.astrosales.model.Flight;
import com.github.rodis00.astrosales.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService implements FlightServiceInterface {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Flight getFlightById(Integer id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
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

    @Override
    public Flight deleteFlight(Integer id) {
        Flight flight = getFlightById(id);
        if (flight != null)
            flightRepository.delete(flight);
        return flight;
    }
}
