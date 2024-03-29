import { Component } from '@angular/core';
import { ProfileComponent } from '../../components/profile/profile.component';
import { ActiveTicketsComponent } from '../../components/active-tickets/active-tickets.component';
import { TicketsHistoryComponent } from '../../components/tickets-history/tickets-history.component';

@Component({
  selector: 'app-account-page',
  standalone: true,
  templateUrl: './account-page.component.html',
  styleUrl: './account-page.component.css',
  imports: [ProfileComponent, ActiveTicketsComponent, TicketsHistoryComponent],
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
