package com.github.rodis00.astrosales.service;

import com.github.rodis00.astrosales.exception.ReservationNotFoundException;
import com.github.rodis00.astrosales.model.Reservation;
import com.github.rodis00.astrosales.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements ReservationServiceInterface {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Override
    public Reservation deleteReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null)
            reservationRepository.delete(reservation);
        return reservation;
    }
}
