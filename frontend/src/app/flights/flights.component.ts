import { Component } from '@angular/core';
import { FlightService } from '../services/flight/flight.service';
import { Flight } from '../models/Flight';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-flights',
  standalone: true,
  imports: [],
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
        console.log(response);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
