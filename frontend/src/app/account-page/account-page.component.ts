import { Component } from '@angular/core';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'app-account-page',
  standalone: true,
  imports: [ProfileComponent],
  templateUrl: './account-page.component.html',
  styleUrl: './account-page.component.css',
})
export class AccountPageComponent {
  profile: string = 'Moje dane';
  activeTickets: string = 'Aktywne bilety';
  ticketsHistory: string = 'Historia zakupów';

  selectedItem: string = this.profile;

  public selectIem(item: string): void {
    this.selectedItem = item;
  }
}
