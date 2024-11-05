package com.github.rodis00.astrosales.reservation;

import com.github.rodis00.astrosales.reservation.entity.Reservation;
import com.github.rodis00.astrosales.reservation.entity.Sector;
import lombok.Data;

@Data
public class ReservationDto {
    private Integer id;
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
