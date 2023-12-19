import { Component } from '@angular/core';
import { FlightService } from '../services/flight/flight.service';
import { Flight } from '../models/Flight';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-flight',
  standalone: true,
  imports: [],
  templateUrl: './flight.component.html',
  styleUrl: './flight.component.css',
})
export class FlightComponent {
  flight!: Flight;

  constructor(
    private flightService: FlightService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe((params) => {
      if (params['id']) {
        this.getFlightById(params['id']);
      }
    });
  }

  private getFlightById(id: number): void {
    this.flightService.getFlightById(id).subscribe((flight: Flight) => {
      this.flight = flight;
      console.log(flight);
    });
  }

  public getRange(start: number, end: number): number[] {
    return Array.from({ length: end - start + 1 }, (_, index) => start + index);
  }
}
