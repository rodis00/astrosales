import { Component } from '@angular/core';
import { FlightService } from '../services/flight/flight.service';
import { Flight } from '../models/Flight';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-flights',
  standalone: true,
  imports: [RouterLink, DatePipe],
  templateUrl: './flights.component.html',
  styleUrl: './flights.component.css',
})
export class FlightsComponent {
  public flights: Flight[] = [];

  constructor(private flightService: FlightService) {
    this.getAvailableFlights();
  }

  public getAvailableFlights(): void {
    this.flightService.getAvailableFlights().subscribe(
      (response: Flight[]) => {
        this.flights = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
