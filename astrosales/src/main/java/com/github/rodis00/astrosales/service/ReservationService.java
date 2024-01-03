package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.ReservationNotFoundException;
import com.github.rodis00.astrosales.model.Reservation;
import com.github.rodis00.astrosales.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements ReservationServiceInterface {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> saveReservations(List<Reservation> reservations) {
        return reservationRepository.saveAll(reservations);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public List<Reservation> getReservationByFlightId(Integer id) {
        return reservationRepository.getReservationByTransaction_FlightId(id);
    }

    @Override
    public Long getCountOfReservedPlaces(Integer id) {
        return reservationRepository.countByTransactionId(id);
    }

    @Override
    public void deleteReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null)
            reservationRepository.delete(reservation);
    }
}
