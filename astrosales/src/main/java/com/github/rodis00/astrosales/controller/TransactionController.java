package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.dto.TransactionDto;
import com.github.rodis00.astrosales.exception.TransactionNotFoundException;
import com.github.rodis00.astrosales.model.Transaction;
import com.github.rodis00.astrosales.service.FlightService;
import com.github.rodis00.astrosales.service.ReservationService;
import com.github.rodis00.astrosales.service.TransactionService;
import com.github.rodis00.astrosales.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final ReservationService reservationService;
    private final TransactionService transactionService;
    private final UserService userService;
    private final FlightService flightService;

    @Autowired
    public TransactionController(
            ReservationService reservationService,
            TransactionService transactionService,
            UserService userService,
            FlightService flightService
    ) {
        this.reservationService = reservationService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.flightService = flightService;
    }

    @PostMapping("")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDateOfTransaction(transaction.getDateOfTransaction());
        newTransaction.setUser(userService.getUserById(transaction.getUser().getId()));
        newTransaction.setFlight(flightService.getFlightById(transaction.getFlight().getId()));
        newTransaction.setAmountOfTickets(transaction.getAmountOfTickets());
        newTransaction.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        newTransaction.setReservations(transaction.getReservations());
        transactionService.saveTransaction(newTransaction);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TransactionDto.from(newTransaction));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(
                            TransactionDto
                                    .from(transactionService.getTransactionById(id))
                    );
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetails());
        }
    }
}
