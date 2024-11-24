import { jwtDecode } from "jwt-decode";
import React, { useState } from "react";
import ActiveTickets from "../../components/active-tickets/ActiveTickets";
import Profile from "../../components/profile/Profile";
import TicketsHistory from "../../components/tickets-history/TicketsHistory";
import TokenService from "../../services/token/TokenService";
import styles from "./AccountPage.module.css";

const AccountPage = () => {
  const tokenService = new TokenService();
  const token = tokenService.getToken();

  const PROFILE = "Moje dane";
  const ACTIVE_TICKETS = "Aktywne bilety";
  const TICKETS_HISTORY = "Historia biletów";

  const [selectedItem, setSelectedItem] = useState(PROFILE);
  const [userId, setUserId] = useState(null);

  if (token && !userId) {
    const decodedToken = jwtDecode(token);
    setUserId(decodedToken.id);
  }

  return (
    <div className={styles.container}>
      <div className={styles.wrapper}>
        <div className={styles.header}>
          <h1>{selectedItem}</h1>
        </div>
        <div className={styles.interface}>
          <ul className={styles.navigation}>
            <li
              onClick={() => setSelectedItem(PROFILE)}
              className={selectedItem === PROFILE ? styles.selected : ""}
            >
              {PROFILE}
            </li>
            <li
              onClick={() => setSelectedItem(ACTIVE_TICKETS)}
              className={selectedItem === ACTIVE_TICKETS ? styles.selected : ""}
            >
              {ACTIVE_TICKETS}
            </li>
            <li
              onClick={() => setSelectedItem(TICKETS_HISTORY)}
              className={
                selectedItem === TICKETS_HISTORY ? styles.selected : ""
              }
            >
              {TICKETS_HISTORY}
            </li>
          </ul>
          <div className={styles.contentWrapper}>
            <div className={styles.content}>
              {selectedItem === PROFILE && <Profile userId={userId} />}
              {selectedItem === ACTIVE_TICKETS && (
                <ActiveTickets userId={userId} />
              )}
              {selectedItem === TICKETS_HISTORY && (
                <TicketsHistory userId={userId} />
              )}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountPage;
