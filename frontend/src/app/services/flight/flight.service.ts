import { Injectable } from '@angular/core';
import { Flight } from '../../models/Flight';
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
}
