import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../auth-context/AuthContext";
import styles from "./Header.module.css";

const Header = () => {
  const title = "AstroSales";

  const { isLoggedIn, isAdmin, logout } = useContext(AuthContext);

  return (
    <header className={styles.header}>
      <div className={styles.container}>
        <Link to={"/"} className={styles.logo}>
          {title}
        </Link>
        <nav className={styles.nav}>
          <ul className={styles.navList}>
            <li className={styles.navItem}>
              <Link to={"/flights"} className={styles.navLink}>
                Loty
              </Link>
            </li>
            {isAdmin && (
              <li className={styles.navItem}>
                <Link to={"/admin"} className={styles.navLink}>
                  Panel
                </Link>
              </li>
            )}
            {isLoggedIn ? (
              <>
                <li className={styles.navItem}>
                  <Link to={"/account"} className={styles.navLink}>
                    Konto
                  </Link>
                </li>
                <li className={styles.navItem}>
                  <Link to={"#"} className={styles.navLink} onClick={logout}>
                    Wyloguj
                  </Link>
                </li>
              </>
            ) : (
              <li className={styles.navItem}>
                <Link to={"/login"} className={styles.navLink}>
                  Zaloguj
                </Link>
              </li>
            )}
          </ul>
        </nav>
      </div>
    </header>
  );
};

export default Header;
