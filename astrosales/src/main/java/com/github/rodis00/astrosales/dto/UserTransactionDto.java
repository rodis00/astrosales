package com.github.rodis00.astrosales.dto;

import com.github.rodis00.astrosales.model.Transaction;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserTransactionDto {
    private Integer id;
    private LocalDateTime dateOfTransaction;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private LocalDateTime dateOfFlight;
    private String startingPlace;
    private String landingPlace;
    private float ticketPrice;
    private float ticketPriceVip;
    private int amountOfTickets;
    private int amountOfTicketsVip;
    private List<ReservationDto> reservationDtos = new ArrayList<>();

    public static UserTransactionDto from(Transaction transaction) {
        UserTransactionDto userTransactionDto = new UserTransactionDto();
        userTransactionDto.setId(transaction.getId());
        userTransactionDto.setDateOfTransaction(transaction.getDateOfTransaction());
        userTransactionDto.setUserEmail(transaction.getUser().getEmail());
        userTransactionDto.setUserFirstName(transaction.getUser().getUserProfile().getFirstName());
        userTransactionDto.setUserLastName(transaction.getUser().getUserProfile().getLastName());
        userTransactionDto.setDateOfFlight(transaction.getFlight().getDateOfFlight());
        userTransactionDto.setStartingPlace(transaction.getFlight().getStartingPlace());
        userTransactionDto.setLandingPlace(transaction.getFlight().getLandingPlace());
        userTransactionDto.setTicketPrice(transaction.getFlight().getTicketPrice());
        userTransactionDto.setTicketPriceVip(transaction.getFlight().getTicketPriceVip());
        userTransactionDto.setAmountOfTickets(transaction.getAmountOfTickets());
        userTransactionDto.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        userTransactionDto.setReservationDtos(
                transaction.getReservations()
                        .stream()
                        .map(ReservationDto::from).toList());
        return userTransactionDto;
    }
}
