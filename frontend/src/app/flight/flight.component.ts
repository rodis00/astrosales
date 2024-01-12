import { Component } from '@angular/core';
import { FlightService } from '../services/flight/flight.service';
import { Flight } from '../models/Flight';
import { ActivatedRoute } from '@angular/router';
import { ReservationService } from '../services/reservation/reservation.service';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-flight',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './flight.component.html',
  styleUrl: './flight.component.css',
  providers: [ReservationService],
})
export class FlightComponent {
  flight!: Flight;
  choosenPlaces: Array<number> = [];
  reservedPlaces!: number[];

  constructor(
    private flightService: FlightService,
    private route: ActivatedRoute,
    private reservationService: ReservationService
  ) {
    this.route.params.subscribe((params) => {
      if (params['id']) {
        this.getFlightById(params['id']);
        this.getReservedPlaces(params['id']);
      }
    });
  }

  private getFlightById(id: number): void {
    this.flightService.getFlightById(id).subscribe((flight: Flight) => {
      this.flight = flight;
    });
  }

  private getReservedPlaces(flightId: number): void {
    this.reservationService.getReservedPlaces(flightId).subscribe(
      (response: number[]) => {
        this.reservedPlaces = response;
        console.log(this.reservedPlaces);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  public getRange(start: number, end: number): number[] {
    return Array.from({ length: end - start + 1 }, (_, index) => start + index);
  }

  public addPlace(place: number): void {
    if (!this.reservedPlaces.includes(place)) {
      if (this.choosenPlaces.includes(place)) {
        let index = this.choosenPlaces.indexOf(place);
        if (index !== -1) {
          this.choosenPlaces.splice(index, 1);
        }
      } else {
        this.choosenPlaces.push(place);
      }
    }
  }
}
