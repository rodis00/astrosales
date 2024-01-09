import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Flight } from '../models/Flight';
import { FlightService } from '../services/flight/flight.service';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [ReactiveFormsModule, DatePipe],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css',
})
export class AdminComponent {
  constructor(private flightService: FlightService) {
    this.getAllFlights();
  }

  flightForm = new FormGroup({
    dateOfFlight: new FormControl('', Validators.required),
    startingPlace: new FormControl('', Validators.required),
    landingPlace: new FormControl('', Validators.required),
    ticketPrice: new FormControl('', Validators.required),
    ticketPriceVip: new FormControl('', Validators.required),
    availabilityOfPlaces: new FormControl('', Validators.required),
  });

  flights: Flight[] = [];

  public handleSubmit(): void {
    let data = this.flightForm.value;
    this.updateFlight(data);
  }

  private updateFlight(flight: any): void {
    this.flightService.addFlight(flight).subscribe(
      (flight: Flight) => {
        alert('Lot został pomyślnie dodany.');
        this.refreshPage();
      },
      (error: HttpErrorResponse) => {
        alert('Nie udało się dodać lotu.');
        console.log(error);
      }
    );
  }

  private getAllFlights(): void {
    this.flightService.getAllFlights().subscribe(
      (flights: Flight[]) => {
        this.flights = flights;
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  private refreshPage(): void {
    setInterval(() => {
      location.reload();
    }, 300);
  }
}
