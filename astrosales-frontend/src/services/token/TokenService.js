import { jwtDecode } from "jwt-decode";

class TokenService {
  constructor() {}

  setToken(token) {
    localStorage.setItem("accessToken", token);
  }

  getToken() {
    const token = localStorage.getItem("accessToken");
    if (token && this.isTokenExpired(token)) {
      this.removeToken();
      return null;
    }
    return token;
  }

  isTokenExpired(token) {
    const exp = jwtDecode(token).exp;
    return Date.now() > exp * 1000;
  }

  removeToken() {
    localStorage.removeItem("accessToken");
  }
}

export default TokenService;
