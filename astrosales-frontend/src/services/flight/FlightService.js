import TokenService from "../token/TokenService";

class FlighService {
  constructor() {
    this.apiUrl = process.env.REACT_APP_API_URL;
    this.tokenService = new TokenService();
  }

  async getAvailableFlights() {
    const res = await fetch(`${this.apiUrl}/flights`, {
      method: "GET",
    });
    return res.json();
  }

  async getFlightById(id) {
    const res = await fetch(`${this.apiUrl}/flights/${id}`, {
      method: "GET",
    });
    return res.json();
  }

  async addFlight(flight) {
    const res = await fetch(`${this.apiUrl}/flights`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${this.tokenService.getToken()}`,
      },
      body: JSON.stringify(flight),
    });
    return res.json();
  }

  async getAllFlights() {
    const res = await fetch(`${this.apiUrl}/flights/all`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${this.tokenService.getToken()}`,
      },
    });
    return res.json();
  }

  async deleteFlightById(id) {
    const res = await fetch(`${this.apiUrl}/flights/${id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${this.tokenService.getToken()}`,
      },
    });
  }
}

export default FlighService;
