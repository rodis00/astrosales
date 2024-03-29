import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private jwtHelper: JwtHelperService = new JwtHelperService();
  constructor() {
    setInterval(() => this.checkTokenExpirationTime(), 1000 * 60);
  }

  public setToken(response: any): void {
    const tokenExpirationTime = 1000 * 60 * 60 * 24;
    sessionStorage.setItem('token', JSON.stringify(response.token));
    sessionStorage.setItem(
      'tokenExpirationTime',
      (Date.now() + tokenExpirationTime).toString()
    );
  }

  public checkTokenExpirationTime(): void {
    const tokenExpirationTime = sessionStorage.getItem('tokenExpirationTime');
    if (tokenExpirationTime && Date.now() > parseInt(tokenExpirationTime)) {
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('tokenExpirationTime');
      location.reload();
    }
  }

  getRolesAndIdFromToken(
    token: string
  ): { roles: string[]; id: string } | null {
    try {
      const decodedToken = this.jwtHelper.decodeToken(token);
      const userRoles = decodedToken.roles[0].authority;
      const userId = decodedToken.userId;

      return { roles: userRoles, id: userId };
    } catch (error) {
      console.error('Błąd odczytu tokena:', error);
      return null;
    }
  }

  public isLogedIn(): boolean {
    return sessionStorage.getItem('token') !== null;
  }

  public logOut(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('tokenExpirationTime');
    location.reload();
  }
}
