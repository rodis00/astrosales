package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Flight;

import java.util.List;

public interface FlightServiceInterface {
    Flight saveFlight(Flight flight);
    Flight getFlightById(Integer id);
    List<Flight> getAllFlights();
    Flight updateFlight(Integer id, Flight flight);
    void deleteFlight(Integer id);
}
