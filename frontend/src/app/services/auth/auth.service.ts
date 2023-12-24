import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/User';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';

  constructor(private http: HttpClient) {}

  public authenticate(user: User): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/auth/authenticate`, user);
  }

  public register(user: User): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/auth/register`, user);
  }
}
