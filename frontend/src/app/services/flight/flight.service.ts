import { Injectable } from '@angular/core';
import { Flight, FlightWithoutId } from '../../models/Flight';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FlightService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';

  constructor(private http: HttpClient) {}

  public getAvailableFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.apiUrl}/flights`);
  }

  public getFlightById(id: number): Observable<Flight> {
    return this.http.get<Flight>(`${this.apiUrl}/flights/${id}`);
  }

  public addFlight(flight: FlightWithoutId): Observable<any> {
    return this.http.post<Flight>(`${this.apiUrl}/flights`, flight);
  }

  public getAllFlights(): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.apiUrl}/flights/all`);
  }

  public deleteFlightById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/flights/${id}`);
  }
}
