import "boxicons";
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import ErrorMessage from "../../components/error/ErrorMessage";
import Loader from "../../components/loader/Loader";
import FlighService from "../../services/flight/FlightService";
import styles from "./FlightPage.module.css";

const FlightPage = () => {
  const flightService = new FlighService();

  const [flights, setFlights] = useState([]);
  const [error, setError] = useState(false);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    const getAvailableFlights = () => {
      flightService
        .getAvailableFlights()
        .then((res) => {
          setFlights(res || []);
          setError(false);
        })
        .catch((err) => {
          setError(true);
          console.log(err);
        })
        .finally(() => {
          setTimeout(() => {
            setIsLoading(false);
          }, 300);
        });
    };
    setIsLoading(true);
    getAvailableFlights();
  }, []);

  if (isLoading) {
    return <Loader />;
  }

  if (error) {
    return <ErrorMessage />;
  }

  return (
    <div className={styles.container}>
      {flights.length > 0 ? (
        <div className={styles.mainHeader}>
          <h1>Dostępne loty</h1>
        </div>
      ) : (
        <h1>Brak dostępnych lotów.</h1>
      )}

      {flights.map((flight) => (
        <Link key={flight.id} to={`/flights/${flight.id}`}>
          <div className={styles.contentContainer}>
            <div className={styles.content}>
              <div className={styles.dateOfFlight}>
                <div className={styles.date}>
                  <box-icon name="calendar"></box-icon>
                  <h2>
                    {new Date(flight.dateOfFlight).toLocaleDateString("pl-PL", {
                      day: "2-digit",
                      month: "2-digit",
                      year: "numeric",
                    })}
                  </h2>
                </div>
                <div className={styles.time}>
                  <box-icon name="time"></box-icon>
                  <h3>
                    {new Date(flight.dateOfFlight).toLocaleTimeString("pl-PL", {
                      hour: "2-digit",
                      minute: "2-digit",
                    })}
                  </h3>
                </div>
              </div>
              <div className={styles.travelTarget}>
                <div className={styles.startPlace}>
                  <h3>Miejsce Startu:</h3>
                  <span>{flight.startingPlace}</span>
                </div>
                <div className={styles.endPlace}>
                  <h3>Miejsce Lądowania:</h3>
                  <span>{flight.landingPlace}</span>
                </div>
              </div>
              <div className={styles.availablePlaces}>
                <span>{flight.availabilityOfPlaces}</span>
              </div>
            </div>

            <div className={styles.image}>
              {(() => {
                switch (flight.landingPlace.toLowerCase()) {
                  case "księżyc":
                    return <img src="/images/moon.jpg" alt="moon image" />;
                  case "mars":
                    return <img src="/images/mars.jpg" alt="mars image" />;
                  case "jowisz":
                    return (
                      <img src="/images/jupyter.jpg" alt="jupiter image" />
                    );
                  default:
                    return (
                      <img src="/images/unknown.jpg" alt="planets image" />
                    );
                }
              })()}
            </div>
          </div>
        </Link>
      ))}
    </div>
  );
};

export default FlightPage;
