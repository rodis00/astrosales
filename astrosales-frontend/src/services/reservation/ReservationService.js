import TokenService from "../token/TokenService";

class ReservationService {
  constructor() {
    this.apiUrl = "http://localhost:8080/astrosales/api/v1";
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
