import { Component } from '@angular/core';
import { UserProfileService } from '../../services/userProfile/user-profile.service';
import { TokenService } from '../../services/token/token.service';
import { UserProfile, UserProfileWithId } from '../../models/UserProfile';
import { HttpErrorResponse } from '@angular/common/http';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { PhonePipe } from '../../custom-pipes/PhonePipe';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [ReactiveFormsModule, PhonePipe],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  providers: [UserProfileService, TokenService],
})
export class ProfileComponent {
  userProfileWithId!: UserProfileWithId;

  authToken: any = sessionStorage.getItem('token');
  token: any = {};

  profileForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    phoneNumber: new FormControl('', Validators.required),
  });

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
      (userProfile: UserProfileWithId) => {
        this.userProfileWithId = userProfile;
        console.log(this.userProfileWithId);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }

  public handleSubmit(): void {
    let formData = this.profileForm.value;
    console.log(formData);
    this.updateProfile(formData);
  }

  private updateProfile(profile: UserProfile): void {
    this.userProfileService
      .updateUserProfile(this.userProfileWithId.id, profile)
      .subscribe(
        (updatedProfile: UserProfileWithId) => {
          this.userProfileWithId = updatedProfile;
          this.refreshPage();
          alert('Profil zaktualizowany.');
        },
        (error: HttpErrorResponse) => {
          console.log(error);
          alert('Błąd aktualizacji profilu.');
        }
      );
  }

  private refreshPage(): void {
    setInterval(() => {
      location.reload();
    }, 300);
  }
}
