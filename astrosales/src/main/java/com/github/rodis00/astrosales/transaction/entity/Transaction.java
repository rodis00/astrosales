package com.github.rodis00.astrosales.transaction.entity;

import com.github.rodis00.astrosales.flight.entity.Flight;
import com.github.rodis00.astrosales.reservation.entity.Reservation;
import com.github.rodis00.astrosales.user.entity.User;
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
    private Integer id;

    private LocalDateTime dateOfTransaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private int amountOfTickets;

    private int amountOfTicketsVip;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "transaction"
    )
    private List<Reservation> reservations = new ArrayList<>();
}
