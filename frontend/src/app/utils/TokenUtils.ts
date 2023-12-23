export class TokenUtils {
  constructor() {
    setInterval(() => this.checkTokenExpirationTime(), 1000 * 60);
  }

  public setToken(response: string): void {
    const tokenExpirationTime = 1000 * 60 * 60 * 24;
    sessionStorage.setItem('token', JSON.stringify(response));
    sessionStorage.setItem(
      'tokenExpirationTime',
      (Date.now() + tokenExpirationTime).toString()
    );
  }

  public checkTokenExpirationTime(): void {
    const tokenExpirationTime = sessionStorage.getItem('tokenExpirationTime');
    if (tokenExpirationTime && Date.now() > parseInt(tokenExpirationTime)) {
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('tokenExpirationTime');
    }
  }
}
