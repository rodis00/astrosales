import { Component, Input } from '@angular/core';
import { FlightTransaction } from '../models/FlightTransaction';

@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [],
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css',
})
export class TicketComponent {
  @Input() tickets!: FlightTransaction[];
  @Input() message!: string;
}
