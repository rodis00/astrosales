import { Injectable } from '@angular/core';
import { UserProfile } from '../../models/UserProfile';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserProfileService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';
  private token = sessionStorage.getItem('token');
  private tmpToken: string =
    'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJ1c2VySWQiOjEsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJBRE1JTiJ9XSwiaWF0IjoxNzA0MTQ1Mjc3LCJleHAiOjE3MDQxNDY3MTd9.Et_0TfcIF_xp7WM0LRFoip7eZ6MtUwxjh0ZJ-oVyPGs';
  private headers = new HttpHeaders({
    Authorization: `Bearer ${this.tmpToken}`,
  });

  private options = {
    headers: this.headers,
  };

  constructor(private http: HttpClient) {}

  public getUserProfile(id: number): Observable<UserProfile> {
    console.log(this.options);
    return this.http.get<UserProfile>(`${this.apiUrl}/user-profiles/${id}`);
  }
}
