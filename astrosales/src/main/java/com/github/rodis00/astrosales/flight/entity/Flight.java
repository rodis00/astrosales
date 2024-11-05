package com.github.rodis00.astrosales.flight.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime dateOfFlight;

    private String startingPlace;

    private String landingPlace;

    private float ticketPrice;

    private float ticketPriceVip;

    private int availabilityOfPlaces;
}
