import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HomeComponent } from './pages/home/home.component';
import { RegisterPageComponent } from './pages/register-page/register-page.component';
import { FlightsComponent } from './pages/flights/flights.component';
import { FlightComponent } from './pages/flight/flight.component';
import { AccountPageComponent } from './pages/account-page/account-page.component';
import { AdminComponent } from './pages/admin/admin.component';
import { userGuard } from './guards/user/user.guard';
import { adminGuard } from './guards/admin/admin.guard';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginPageComponent },
  { path: 'register', component: RegisterPageComponent },
  { path: 'flights', component: FlightsComponent },
  { path: 'flight/:id', component: FlightComponent },
  {
    path: 'account',
    canActivate: [userGuard],
    component: AccountPageComponent,
  },
  { path: 'admin', canActivate: [adminGuard], component: AdminComponent },
];
