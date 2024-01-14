import { Component } from '@angular/core';
import { TicketComponent } from '../ticket/ticket.component';
import { FlightTransaction } from '../../models/FlightTransaction';
import { TokenService } from '../../services/token/token.service';
import { FlightTransactionService } from '../../services/flightTransaction/flight-transaction.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-tickets-history',
  standalone: true,
  templateUrl: './tickets-history.component.html',
  styleUrl: './tickets-history.component.css',
  imports: [TicketComponent],
  providers: [TokenService, FlightTransactionService],
})
export class TicketsHistoryComponent {
  tickets!: FlightTransaction[];
  message: string = 'Brak historii zakupów.';

  private authToken: any = sessionStorage.getItem('token');
  private userDetails: any = {};

  constructor(
    private tokenService: TokenService,
    private transactionService: FlightTransactionService
  ) {
    this.userDetails = tokenService.getRolesAndIdFromToken(this.authToken);
    if (this.authToken != null) {
      this.getTicketsHistory(this.userDetails.id);
    }
  }

  private getTicketsHistory(id: number): void {
    this.transactionService.getuserFlightHistoryTransactions(id).subscribe(
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
