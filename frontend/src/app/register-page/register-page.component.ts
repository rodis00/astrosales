import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../models/User';
import { AuthService } from '../services/auth/auth.service';
import { TokenService } from '../services/token/token.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.css',
})
export class RegisterPageComponent {
  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  registerForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  user: User = new User('', '');
  errorMessage: string = '';

  public handleSubmit(): void {
    this.user = new User(
      this.registerForm.value.email,
      this.registerForm.value.password
    );
    this.register(this.user);
  }

  private register(user: User): void {
    this.authService.register(user).subscribe(
      (token: string) => {
        if (token != '') {
          this.tokenService.setToken(token);
          this.router.navigateByUrl('/');
          this.refreshPage();
        }
      },
      (error: HttpErrorResponse) => {
        console.log(error);
        this.errorMessage = error.error.responseMessage;
      }
    );
  }

  private refreshPage(): void {
    setInterval(() => {
      location.reload();
    }, 300);
  }
}
