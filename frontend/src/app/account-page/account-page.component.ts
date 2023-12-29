import { Component } from '@angular/core';

@Component({
  selector: 'app-account-page',
  standalone: true,
  imports: [],
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
