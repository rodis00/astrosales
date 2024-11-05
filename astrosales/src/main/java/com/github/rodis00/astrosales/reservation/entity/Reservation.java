package com.github.rodis00.astrosales.reservation.entity;

import com.github.rodis00.astrosales.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    private Sector sector;

    private int place;
}
