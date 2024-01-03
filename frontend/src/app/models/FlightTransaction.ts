export interface FlightTransaction {
  id: number;
  dateOfTransaction: Date;
  userEmail: string;
  userFirstName: string;
  userLastName: string;
  dateOfFlight: Date;
  startingPlace: string;
  landingPlace: string;
  ticketPrice: number;
  ticketPriceVip: number;
  amountOfTickets: number;
  amountOfTicketsVip: number;
  reservationDtos: Reservation[];
}

export interface Reservation {
  id: number;
  sector: string;
  place: number;
}
