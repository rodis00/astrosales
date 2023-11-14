package com.github.rodis00.astrosales.repository;

import com.github.rodis00.astrosales.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f FROM Flight f where f.dateOfFlight >= :today")
    List<Flight> getAvailableFlights(@Param("today") LocalDateTime today);
}
