import { Injectable } from '@angular/core';
import { UserProfile, UserProfileWithId } from '../../models/UserProfile';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserProfileService {
  private apiUrl: string = 'http://localhost:8080/astrosales/api/v1';
  private token = sessionStorage.getItem('token');

  private headers = new HttpHeaders({
    Authorization: `Bearer ${this.token}`,
  });

  private options = {
    headers: this.headers,
  };

  constructor(private http: HttpClient) {}

  public getUserProfile(id: number): Observable<UserProfileWithId> {
    console.log(this.options);
    return this.http.get<UserProfileWithId>(
      `${this.apiUrl}/user-profiles/${id}`
    );
  }

  public updateUserProfile(
    id: number,
    userProfile: UserProfile
  ): Observable<UserProfileWithId> {
    return this.http.put<UserProfileWithId>(
      `${this.apiUrl}/user-profiles/${id}`,
      userProfile
    );
  }
}
