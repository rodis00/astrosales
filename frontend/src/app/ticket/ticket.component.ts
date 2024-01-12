import { Component, Input } from '@angular/core';
import { FlightTransaction } from '../models/FlightTransaction';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-ticket',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './ticket.component.html',
  styleUrl: './ticket.component.css',
})
export class TicketComponent {
  @Input() tickets!: FlightTransaction[];
  @Input() message!: string;
}
