package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.dto.TransactionDto;
import com.github.rodis00.astrosales.exception.TransactionNotFoundException;
import com.github.rodis00.astrosales.model.Reservation;
import com.github.rodis00.astrosales.model.Transaction;
import com.github.rodis00.astrosales.service.ReservationService;
import com.github.rodis00.astrosales.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final ReservationService reservationService;
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(ReservationService reservationService, TransactionService transactionService) {
        this.reservationService = reservationService;
        this.transactionService = transactionService;
    }

    @PostMapping("")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody Transaction transaction) {
        Transaction newTransaction = transactionService.saveTransaction(transaction);
        List<Reservation> reservations = reservationService.saveReservations(transaction.getReservations());
        newTransaction.setReservations(reservations);
        transactionService.saveTransaction(newTransaction);
        return ResponseEntity
                .status(HttpStatus.OK)
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
