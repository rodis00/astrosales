package com.github.rodis00.astrosales.transaction;

import com.github.rodis00.astrosales.apiResponse.ApiResponseDto;
import com.github.rodis00.astrosales.flight.entity.Flight;
import com.github.rodis00.astrosales.flight.FlightService;
import com.github.rodis00.astrosales.reservation.entity.Reservation;
import com.github.rodis00.astrosales.reservation.ReservationService;
import com.github.rodis00.astrosales.transaction.entity.Transaction;
import com.github.rodis00.astrosales.user.UserService;
import com.github.rodis00.astrosales.user.entity.User;
import com.github.rodis00.astrosales.userProfile.entity.UserProfile;
import com.github.rodis00.astrosales.flight.utils.FlightUtils;
import com.github.rodis00.astrosales.userProfile.utils.UserProfileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("astrosales/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;
    private final FlightService flightService;
    private final ReservationService reservationService;

    @Autowired
    public TransactionController(
            TransactionService transactionService,
            UserService userService,
            FlightService flightService,
            ReservationService reservationService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.flightService = flightService;
        this.reservationService = reservationService;
    }

    @PostMapping("")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        User user = userService.getUserById(transaction.getUser().getId());
        if (user != null && userProfileIsNotEmpty(user.getUserProfile())) {
            Transaction newTransaction = buildTransactionFromRequest(transaction);
            if (!isFlightDateAfterToday(newTransaction.getFlight().getDateOfFlight())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponseDto(
                                FlightUtils.FLIGHT_DATE_IN_THE_PAST_CODE,
                                FlightUtils.FLIGHT_DATE_IN_THE_PAST_MESSAGE
                        ));
            } else {
                if (!isEnoughPlaces(newTransaction)) {
                    return ResponseEntity
                            .status(HttpStatus.BAD_REQUEST)
                            .body(new ApiResponseDto(
                                    FlightUtils.NO_AVAILABLE_PLACES_CODE,
                                    FlightUtils.NO_AVAILABLE_PLACES_MESSAGE
                            ));
                } else {
                    transactionService.saveTransaction(newTransaction);
                    Flight flight = flightService.getFlightById(transaction.getFlight().getId());
                    flight.setAvailabilityOfPlaces(
                            flight.getAvailabilityOfPlaces() - getTransactionTicketsCount(
                                    newTransaction.getAmountOfTickets(),
                                    newTransaction.getAmountOfTicketsVip()
                            )
                    );
                    flightService.saveFlight(flight);
                    return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .body(TransactionDto.from(newTransaction));
                }
            }
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto(
                            UserProfileUtils.USER_PROFILE_EMPTY_CODE,
                            UserProfileUtils.USER_PROFILE_EMPTY_MESSAGE
                    ));
        }
    }

    private boolean userProfileIsNotEmpty(UserProfile userProfile) {
        return userProfile.getFirstName() != null && userProfile.getLastName() != null;
    }

    private boolean isEnoughPlaces(Transaction transaction) {
        List<Integer> transactionsList =
                transactionService.getTransactionsByFlightId(transaction.getFlight().getId());
        if (transactionsList.isEmpty())
            return true;
        else {
            int transactionTicketsCount =
                    getTransactionTicketsCount(transaction.getAmountOfTickets(), transaction.getAmountOfTicketsVip());
            int availablePlacesCount =
                    getAvailablePlacesCount(transactionsList, transaction.getFlight().getAvailabilityOfPlaces());
            return transactionTicketsCount <= availablePlacesCount;
        }
    }

    private int getTransactionTicketsCount(int amountOfTickets, int amountOfTicketsVip) {
        return amountOfTickets + amountOfTicketsVip;
    }

    private int getAvailablePlacesCount(List<Integer> transactionsList, int totalPlacesInFlight) {
        Long reservedPlaces = 0L;
        for (int transactionId : transactionsList) {
            reservedPlaces += reservationService.getCountOfReservedPlaces(transactionId);
        }
        return (int) (totalPlacesInFlight - reservedPlaces);
    }

    private boolean isFlightDateAfterToday(LocalDateTime flightDate) {
        return flightDate.isAfter(LocalDateTime.now());
    }

    private Transaction buildTransactionFromRequest(Transaction transaction) {
        Transaction newTransaction = new Transaction();
        List<Reservation> reservationEntities = new ArrayList<>();
        for (Reservation r : transaction.getReservations()) {
            Reservation reservation = new Reservation();
            reservation.setPlace(r.getPlace());
            reservation.setSector(r.getSector());
            reservation.setTransaction(newTransaction);
            reservationEntities.add(reservation);
        }
        newTransaction.setDateOfTransaction(transaction.getDateOfTransaction());
        newTransaction.setUser(userService.getUserById(transaction.getUser().getId()));
        newTransaction.setFlight(flightService.getFlightById(transaction.getFlight().getId()));
        newTransaction.setAmountOfTickets(transaction.getAmountOfTickets());
        newTransaction.setAmountOfTicketsVip(transaction.getAmountOfTicketsVip());
        newTransaction.setReservations(reservationEntities);
        return newTransaction;
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Integer id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(TransactionDto
                        .from(transactionService.getTransactionById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTransactionsByUserId(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getTransactionByUserId(userId)
                        .stream()
                        .map(UserTransactionDto::from));
    }

    @GetMapping("/user/{userId}/active")
    public ResponseEntity<?> getTransactionsByUserIdAndDateOfFlightGrowerEqualToday(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transactionService.getTransactionByUserIdGreaterOrEqualToday(userId, LocalDateTime.now())
                        .stream()
                        .map(UserTransactionDto::from));
    }
}
