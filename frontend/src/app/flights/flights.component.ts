import { Component } from '@angular/core';
import { FlightService } from '../services/flight/flight.service';
import { Flight } from '../models/Flight';
import { HttpErrorResponse } from '@angular/common/http';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-flights',
  standalone: true,
  imports: [RouterLink],
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
        this.flights = response.map((flight: Flight) => {
          return {
            ...flight,
            dateOfFlight: new Date(flight.dateOfFlight),
          };
        });
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
