package com.github.rodis00.astrosales.model;

import com.github.rodis00.astrosales.dto.UserDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime dateOfTransaction;
    @OneToMany
    @JoinColumn(name = "user_id")
    private UserDto userDto;
    @OneToMany
    @JoinColumn(name = "flight_id")
    private Flight flight;
    private int amountOfTickets;
    private int amountOfTicketsVip;
}
