package com.github.rodis00.astrosales.dto;

import com.github.rodis00.astrosales.model.Reservation;
import com.github.rodis00.astrosales.model.Sector;
import lombok.Data;

@Data
public class ReservationDto {
    private int id;
    private Sector sector;
    private int place;

    public static ReservationDto from(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setSector(reservation.getSector());
        reservationDto.setPlace(reservation.getPlace());
        return reservationDto;
    }
}
