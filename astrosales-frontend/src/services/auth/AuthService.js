import TokenService from "../token/TokenService";

class AuthService {
  constructor() {
    this.apiUrl = "http://localhost:8080/astrosales/api/v1";
    this.tokenService = new TokenService();
  }

  async login(user) {
    const res = await fetch(`${this.apiUrl}/auth/authenticate`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });

    return res.json();
  }

  async getAuthorities() {
    const res = await fetch(`${this.apiUrl}/auth/authorities`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${this.tokenService.getToken()}`,
      },
    });

    return res.json();
  }
}

export default AuthService;
