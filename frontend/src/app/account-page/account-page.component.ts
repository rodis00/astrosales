import { Component } from '@angular/core';
import { ProfileComponent } from '../profile/profile.component';
import { UserProfileService } from '../services/userProfile/user-profile.service';
import { TokenService } from '../services/token/token.service';
import { UserProfile } from '../models/UserProfile';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-account-page',
  standalone: true,
  imports: [ProfileComponent],
  templateUrl: './account-page.component.html',
  styleUrl: './account-page.component.css',
  providers: [UserProfileService, TokenService],
})
export class AccountPageComponent {
  profile: string = 'Moje dane';
  activeTickets: string = 'Aktywne bilety';
  ticketsHistory: string = 'Historia zakupów';

  selectedItem: string = this.profile;

  userProfile?: UserProfile;

  authToken: any = sessionStorage.getItem('token');
  token: any = {};

  constructor(
    private userProfileService: UserProfileService,
    private tokenService: TokenService
  ) {
    this.token = tokenService.getRolesAndIdFromToken(this.authToken);
    if (this.token != null) {
      this.getProfile();
    }
  }

  public selectIem(item: string): void {
    this.selectedItem = item;
  }

  private getProfile(): void {
    this.userProfileService.getUserProfile(this.token.id).subscribe(
      (userProfile: UserProfile) => {
        this.userProfile = userProfile;
        console.log(this.userProfile);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
}
