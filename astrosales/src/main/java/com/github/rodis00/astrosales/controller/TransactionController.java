package com.github.rodis00.astrosales.controller;

import com.github.rodis00.astrosales.dto.ApiResponseDto;
import com.github.rodis00.astrosales.dto.TransactionDto;
import com.github.rodis00.astrosales.exception.TransactionNotFoundException;
import com.github.rodis00.astrosales.exception.UserNotFoundException;
import com.github.rodis00.astrosales.model.Transaction;
import com.github.rodis00.astrosales.model.User;
import com.github.rodis00.astrosales.model.UserProfile;
import com.github.rodis00.astrosales.service.FlightService;
import com.github.rodis00.astrosales.service.TransactionService;
import com.github.rodis00.astrosales.service.UserService;
import com.github.rodis00.astrosales.utils.UserProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final FlightService flightService;

    @Autowired
    public TransactionController(
            TransactionService transactionService,
            UserService userService,
            FlightService flightService
    ) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.flightService = flightService;
    }

    @PostMapping("")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        try {
            User user = userService.getUserById(transaction.getUser().getId());
            if (user != null && userProfileIsNotEmpty(user.getUserProfile())) {
                Transaction newTransaction = buildTransactionFromRequest(transaction);
                transactionService.saveTransaction(newTransaction);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(TransactionDto.from(newTransaction));
            } else {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponseDto(
                                UserProfileUtils.USER_PROFILE_EMPTY_CODE,
                                UserProfileUtils.USER_PROFILE_EMPTY_MESSAGE
                        ));
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetail());
        }
    }

    private boolean userProfileIsNotEmpty(UserProfile userProfile) {
        return userProfile.getFirstName() != null && userProfile.getLastName() != null;
    }

    private Transaction buildTransactionFromRequest(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDateOfTransaction(transaction.getDateOfTransaction());
        newTransaction.setUser(userService.getUserById(transaction.getUser().getId()));
        newTransaction.setFlight(flightService.getFlightById(transaction.getFlight().getId()));
        newTransaction.setAmountOfTickets(transaction.getAmountOfTickets());
        newTransaction.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        newTransaction.setReservations(transaction.getReservations());
        return newTransaction;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(TransactionDto
                            .from(transactionService.getTransactionById(id)));
        } catch (TransactionNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorDetails());
        }
    }
}
