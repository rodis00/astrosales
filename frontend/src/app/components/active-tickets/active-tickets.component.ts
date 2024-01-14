import { Component } from '@angular/core';
import { TicketComponent } from '../ticket/ticket.component';
import { FlightTransaction } from '../../models/FlightTransaction';
import { FlightTransactionService } from '../../services/flightTransaction/flight-transaction.service';
import { HttpErrorResponse } from '@angular/common/http';
import { TokenService } from '../../services/token/token.service';

@Component({
  selector: 'app-active-tickets',
  standalone: true,
  templateUrl: './active-tickets.component.html',
  styleUrl: './active-tickets.component.css',
  imports: [TicketComponent],
  providers: [FlightTransactionService, TokenService],
})
export class ActiveTicketsComponent {
  tickets!: FlightTransaction[];
  message: string = 'Brak aktywnych biletów.';

  private authToken: any = sessionStorage.getItem('token');
  private userDetails: any = {};

  constructor(
    private transactionService: FlightTransactionService,
    private tokenService: TokenService
  ) {
    this.userDetails = tokenService.getRolesAndIdFromToken(this.authToken);
    if (this.userDetails.id != null) {
      this.getActiveTransactions(this.userDetails.id);
    }
  }

  private getActiveTransactions(id: number): void {
    this.transactionService.getUserFlightActiveTransactions(id).subscribe(
      (transactions: FlightTransaction[]) => {
        this.tickets = transactions.map((transaction: FlightTransaction) => {
          return {
            ...transaction,
            dateOfFlight: new Date(transaction.dateOfFlight),
          };
        });
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    );
  }
}
