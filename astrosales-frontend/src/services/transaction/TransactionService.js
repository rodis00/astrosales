import TokenService from "../token/TokenService";

class TransactionService {
  constructor() {
    this.apiUrl = process.env.REACT_APP_API_URL;
    this.tokenService = new TokenService();
  }

  async getUserActiveTransactions(userId) {
    const res = await fetch(
      `${this.apiUrl}/transactions/user/${userId}/active`,
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${this.tokenService.getToken()}`,
        },
      }
    );
    return res.json();
  }

  async getUserTransactionHistory(userId) {
    const res = await fetch(`${this.apiUrl}/transactions/user/${userId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${this.tokenService.getToken()}`,
      },
    });
    return res.json();
  }
}

export default TransactionService;
