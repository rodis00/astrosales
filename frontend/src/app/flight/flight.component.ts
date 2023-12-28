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
  choosenPlaces: Array<number> = [];

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
      flight.dateOfFlight = new Date(flight.dateOfFlight);
      this.flight = flight;
    });
  }

  public getRange(start: number, end: number): number[] {
    return Array.from({ length: end - start + 1 }, (_, index) => start + index);
  }

  public addPlace(place: number) {
    if (this.choosenPlaces.includes(place)) {
      let index = this.choosenPlaces.indexOf(place);
      if (index !== -1) {
        this.choosenPlaces.splice(index, 1);
        console.log(this.choosenPlaces);
      }
    } else {
      this.choosenPlaces.push(place);
      console.log(this.choosenPlaces);
    }
  }
}
