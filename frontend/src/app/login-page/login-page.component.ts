import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../models/User';
import { AuthService } from '../services/auth/auth.service';
import { TokenService } from '../services/token/token.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(
    private authService: AuthService,
    private router: Router,
    private tokenService: TokenService
  ) {}

  user: User = new User('', '');

  public handleSubmit() {
    this.user = new User(
      this.loginForm.value.email,
      this.loginForm.value.password
    );
    this.authenticate(this.user);
  }

  public authenticate(user: User): void {
    this.authService.authenticate(user).subscribe(
      (token: string) => {
        if (token != '') {
          this.router.navigateByUrl('/');
          this.tokenService.setToken(token);
          this.refreshPage();
          // issue: token is not remove after refreshPage()
        }
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        alert(error.error.responseMessage);
      }
    );
  }

  private refreshPage(): void {
    setTimeout(() => {
      location.reload();
    }, 100);
  }
}
