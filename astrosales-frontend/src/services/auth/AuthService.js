import TokenService from "../token/TokenService";

class AuthService {
  constructor() {
    this.apiUrl = process.env.REACT_APP_API_URL;
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

  async register(user) {
    const res = await fetch(`${this.apiUrl}/auth/register`, {
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
