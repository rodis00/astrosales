package com.github.rodis00.astrosales.reservation;

import com.github.rodis00.astrosales.reservation.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("astrosales/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Integer>> reservedPlaces(@PathVariable Integer flightId) {
        List<Integer> places = new ArrayList<>();
        for (Reservation reservation : reservationService.getReservationByFlightId(flightId)) {
            places.add(reservation.getPlace());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(places);
    }
}
