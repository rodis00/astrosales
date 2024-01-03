import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ReservationService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';

  constructor(private http: HttpClient) {}

  public getReservedPlaces(flightId: number): Observable<[]> {
    return this.http.get<[]>(`${this.apiUrl}/reservations/flight/${flightId}`);
  }
}
