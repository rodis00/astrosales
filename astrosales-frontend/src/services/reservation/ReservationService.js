import TokenService from "../token/TokenService";

class ReservationService {
  constructor() {
    this.apiUrl = process.env.REACT_APP_API_URL;
    this.tokenService = new TokenService();
  }

  async getReservedPlaces(flightId) {
    const res = await fetch(`${this.apiUrl}/reservations/flight/${flightId}`, {
      method: "GET",
    });
    return res.json();
  }
}

export default ReservationService;
