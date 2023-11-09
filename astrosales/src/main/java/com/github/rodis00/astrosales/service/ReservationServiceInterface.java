package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.model.Reservation;

public interface ReservationServiceInterface {
    Reservation saveReservation(Reservation reservation);
    Reservation getReservationById(Integer id);
    Reservation deleteReservation(Integer id);
}
