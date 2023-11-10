package com.github.rodis00.astrosales.dto;

import com.github.rodis00.astrosales.model.Flight;
import com.github.rodis00.astrosales.model.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionDto {
    private int id;
    private LocalDateTime dateOfTransaction;
    private UserDto userDto;
    private Flight flight;
    private int amountOfTickets;
    private int amountOfTicketsVip;
    private List<ReservationDto> reservationDtos = new ArrayList<>();

    public static TransactionDto from(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setDateOfTransaction(transaction.getDateOfTransaction());
        transactionDto.setUserDto(UserDto.from(transaction.getUser()));
        transactionDto.setFlight(transaction.getFlight());
        transactionDto.setAmountOfTickets(transaction.getAmountOfTickets());
        transactionDto.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        transactionDto.setReservationDtos(transaction.getReservations().stream().map(ReservationDto::from).toList());
        return transactionDto;
    }
}
