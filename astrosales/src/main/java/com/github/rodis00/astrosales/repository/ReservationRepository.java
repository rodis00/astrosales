package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Long countByTransactionId(Integer id);
}
