package com.github.rodis00.astrosales.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue
    private int id;
    @Enumerated(EnumType.STRING)
    private Sector sector;
    private int place;
}
