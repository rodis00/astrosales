export interface Flight {
  id: number;
  dateOfFlight: Date;
  startingPlace: string;
  landingPlace: string;
  ticketPrice: number;
  ticketPriceVip: number;
  availabilityOfPlaces: number;
}

export interface FlightWithoutId {
  dateOfFlight: string;
  startingPlace: string;
  landingPlace: string;
  ticketPrice: string;
  ticketPriceVip: string;
  availabilityOfPlaces: string;
}
