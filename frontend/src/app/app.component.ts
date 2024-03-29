import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './pages/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { FlightService } from './services/flight/flight.service';
import { AuthService } from './services/auth/auth.service';
import { TokenService } from './services/token/token.service';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  imports: [
    CommonModule,
    RouterOutlet,
    HeaderComponent,
    HomeComponent,
    HttpClientModule,
  ],
  providers: [FlightService, AuthService, TokenService],
})
export class AppComponent {
  title = 'frontend';
}
