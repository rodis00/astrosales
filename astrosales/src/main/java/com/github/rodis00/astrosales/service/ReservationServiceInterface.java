package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Reservation;

import java.util.List;

public interface ReservationServiceInterface {
    List<Reservation> saveReservations(List<Reservation> reservations);
    Reservation getReservationById(Integer id);
    List<Reservation> getReservationByFlightId(Integer id);
    Long getCountOfReservedPlaces(Integer id);
    void deleteReservation(Integer id);
}
