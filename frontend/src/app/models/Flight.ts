export interface Flight {
  id: number;
  dateOfFlight: Date;
  startingPlace: string;
  landingPlace: string;
  ticketPrice: number;
  ticketPriceVip: number;
  availabilityOfPlaces: number;
}
