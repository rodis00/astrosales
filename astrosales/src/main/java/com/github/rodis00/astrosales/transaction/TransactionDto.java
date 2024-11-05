package com.github.rodis00.astrosales.transaction;

import com.github.rodis00.astrosales.reservation.ReservationDto;
import com.github.rodis00.astrosales.user.UserDto;
import com.github.rodis00.astrosales.flight.entity.Flight;
import com.github.rodis00.astrosales.transaction.entity.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class TransactionDto {
    private Integer id;
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
        transactionDto.setUserDto(
                transaction.getUser() == null ? null : UserDto.from(transaction.getUser())
        );
        transactionDto.setFlight(transaction.getFlight());
        transactionDto.setAmountOfTickets(transaction.getAmountOfTickets());
        transactionDto.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        transactionDto.setReservationDtos(transaction.getReservations().stream().map(ReservationDto::from).toList());
        return transactionDto;
    }
}
