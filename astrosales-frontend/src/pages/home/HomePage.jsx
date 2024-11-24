import React from "react";
import { Link } from "react-router-dom";
import astronaut from "../../assets/images/astronaut-image.png";
import ship from "../../assets/images/space-ship-image.png";
import styles from "./HomePage.module.css";

const HomePage = () => {
  return (
    <>
      <section className={styles.aboutUs}>
        <div className={styles.container1}>
          <div className={styles.image}>
            <img src={astronaut} alt="astrounatu image" />
          </div>
          <div className={styles.content}>
            <p>
              Jesteśmy AstroSales - zespół założony z myślą o fantastycznych i
              nieosiągalnych jak dotąd lotach komsicznych. Podążaj za swoimi
              marzeniami i osiągaj to co niedostępne dla innych. Razem z nami
              sięgnij gwiazd i zwiedzaj inne planety. Pamiętaj "Jutro jest
              dziś"!
            </p>
            <span>
              <Link to={"/flights"}>Kup bilet</Link>i rozpocznij gwiezdną
              przygodę!
            </span>
          </div>
        </div>
      </section>

      <section className={styles.ourShips}>
        <div className={styles.container2}>
          <div className={styles.content}>
            <p>
              Zawsze zastanawiałeś/aś się jak to może być na innej planecie?
              Teraz masz możliwość to sprawdzić! Nasze w pełni bezpieczne promy
              udostępnią ci bardzo przyjemny i komfortowy lot. Nie zwlekaj
              dłużej i spełniaj marzenia z nami!
            </p>
          </div>
          <div className={styles.image}>
            <img src={ship} alt="astronaut image" />
          </div>
        </div>
      </section>
    </>
  );
};

export default HomePage;
