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
  userDetails: any = {};
  isLoggedIn: boolean = false;
  isAdmin: boolean = false;

  constructor(private tokenService: TokenService) {
    if (tokenService.isLogedIn()) {
      this.isLoggedIn = true;
      let token: any = sessionStorage.getItem('token');
      this.userDetails = tokenService.getRolesAndIdFromToken(token);
      if (token && this.userDetails.roles.includes('ROLE_ADMIN')) {
        this.isAdmin = true;
      }
    }
  }

  public logout(): void {
    this.tokenService.logOut();
  }
}
