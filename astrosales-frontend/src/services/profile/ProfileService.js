import TokenService from "../token/TokenService";

class ProfileService {
  constructor() {
    this.apiUrl = "http://localhost:8080/astrosales/api/v1";
    this.tokenService = new TokenService();
    this.headers = {
      "Content-Type": "application/json",
      Authorization: `Bearer ${this.tokenService.getToken()}`,
    };
  }

  async getUserProfile(id) {
    const res = await fetch(`${this.apiUrl}/user-profiles/${id}`, {
      method: "GET",
      headers: this.headers,
    });
    return res.json();
  }

  async updateUserProfile(id, userProfile) {
    const res = await fetch(`${this.apiUrl}/user-profiles/${id}`, {
      method: "PUT",
      headers: this.headers,
      body: JSON.stringify(userProfile),
    });
    return res.json();
  }

  async partialUpdate(id, userProfile) {
    const res = await fetch(`${this.apiUrl}/user-profiles/${id}`, {
      method: "PATCH",
      headers: this.headers,
      body: JSON.stringify(userProfile),
    });
    return res.json();
  }
}

export default ProfileService;
