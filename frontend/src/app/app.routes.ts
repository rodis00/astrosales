import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HomeComponent } from './home/home.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import { FlightsComponent } from './flights/flights.component';
import { FlightComponent } from './flight/flight.component';
import { AccountPageComponent } from './account-page/account-page.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'flights', component: FlightsComponent },
  { path: 'flight/:id', component: FlightComponent },
  { path: 'account', component: AccountPageComponent },
];
