import { Component } from '@angular/core';
import { UserProfileService } from '../services/userProfile/user-profile.service';
import { TokenService } from '../services/token/token.service';
import { UserProfile } from '../models/UserProfile';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  providers: [UserProfileService, TokenService],
})
export class ProfileComponent {
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
