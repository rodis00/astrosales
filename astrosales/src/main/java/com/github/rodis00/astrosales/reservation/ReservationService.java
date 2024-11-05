package com.github.rodis00.astrosales.reservation;

import com.github.rodis00.astrosales.reservation.exception.ReservationNotFoundException;
import com.github.rodis00.astrosales.reservation.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> saveReservations(List<Reservation> reservationEntities) {
        return reservationRepository.saveAll(reservationEntities);
    }

    public Reservation getReservationById(Integer id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
    }

    public List<Reservation> getReservationByFlightId(Integer id) {
        return reservationRepository.getReservationByTransaction_FlightId(id);
    }

    public Long getCountOfReservedPlaces(Integer id) {
        return reservationRepository.countByTransactionId(id);
    }

    public void deleteReservation(Integer id) {
        Reservation reservation = getReservationById(id);
        if (reservation != null)
            reservationRepository.delete(reservation);
    }
}
