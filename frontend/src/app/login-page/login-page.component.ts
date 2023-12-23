import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../models/User';
import { AuthService } from '../services/auth/auth.service';
import { TokenUtils } from '../utils/TokenUtils';

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

  constructor(private authService: AuthService, private router: Router) {}

  user: User = new User('', '');
  tokenUtils: TokenUtils = new TokenUtils();

  public handleSubmit() {
    this.user = new User(
      this.loginForm.value.email,
      this.loginForm.value.password
    );
    this.authenticate(this.user);
  }

  public authenticate(user: User): void {
    this.authService.authenticate(user).subscribe((token: string) => {
      if (token != '') {
        this.tokenUtils.setToken(token);
        this.router.navigateByUrl('/');
      }
    });
  }
}
