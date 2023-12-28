import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { TokenService } from '../services/token/token.service';
@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  providers: [TokenService],
})
export class HeaderComponent {
  public token: any = sessionStorage.getItem('token');

  userDetails: any = {};

  constructor(private tokenService: TokenService) {
    this.userDetails = tokenService.getRolesAndIdFromToken(this.token);
    console.log(this.userDetails);
  }

  public logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('tokenExpirationTime');
    location.reload();
  }
}
