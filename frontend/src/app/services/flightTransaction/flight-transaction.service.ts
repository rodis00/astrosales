import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FlightTransaction } from '../../models/FlightTransaction';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FlightTransactionService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';

  constructor(private http: HttpClient) {}

  public getUserFlightActiveTransactions(
    id: number
  ): Observable<FlightTransaction[]> {
    return this.http.get<FlightTransaction[]>(
      `${this.apiUrl}/transactions/user/${id}/active`
    );
  }

  public getuserFlightHistoryTransactions(
    id: number
  ): Observable<FlightTransaction[]> {
    return this.http.get<FlightTransaction[]>(
      `${this.apiUrl}/transactions/user/${id}`
    );
  }
}
