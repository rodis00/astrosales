package com.github.rodis00.astrosales.reservation;

import com.github.rodis00.astrosales.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Long countByTransactionId(Integer id);

    List<Reservation> getReservationByTransaction_FlightId(Integer id);
}
