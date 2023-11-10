package com.github.rodis00.astrosales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime dateOfTransaction;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private int amountOfTickets;
    private int amountOfTicketsVip;
    @OneToMany
    @JoinColumn(name = "transaction_id")
    private List<Reservation> reservations = new ArrayList<>();
}
