import React, { useEffect, useState } from "react";
import FlightService from "../../services/flight/FlightService";
import styles from "./AdminPage.module.css";

const AdminPage = () => {
  const flightService = new FlightService();
  const [flights, setFlights] = useState([]);
  const [flightForm, setFlightForm] = useState({
    dateOfFlight: "",
    ticketPrice: "",
    startingPlace: "",
    ticketPriceVip: "",
    landingPlace: "",
    availabilityOfPlaces: "",
  });

  const message = "Brak lotów do edycji";

  useEffect(() => {
    flightService
      .getAllFlights()
      .then((res) => {
        setFlights(res);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    flightService.addFlight(flightForm);
    updateFlights();
    alert("Lot został utworzony.");
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFlightForm({ ...flightForm, [name]: value });
  };

  const deleteFlight = (id) => {
    flightService.deleteFlightById(id);
    updateFlights();
    alert("Lot został usunięty");
  };

  const updateFlights = async () => {
    flightService
      .getAllFlights()
      .then((res) => {
        setFlights(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className={styles.container}>
      <div className={styles.mainHeader}>
        <h1>Edytuj loty</h1>
      </div>
      <div className={styles.addFlight}>
        <form onSubmit={handleSubmit}>
          <div className={styles.date}>
            <label htmlFor="dateOfFlight">Data lotu:</label>
            <input
              type="datetime-local"
              id="dateOfFlight"
              name="dateOfFlight"
              value={flightForm.dateOfFlight}
              onChange={handleChange}
            />
          </div>

          <div className={styles.price}>
            <label htmlFor="ticketPrice">Cena biletu Standard:</label>
            <select
              name="ticketPrice"
              id="ticketPrice"
              value={flightForm.ticketPrice}
              onChange={handleChange}
            >
              <option value="" hidden>
                Wybierz...
              </option>
              <option value="180.50">150,50 zł</option>
              <option value="260.18">260,18 zł</option>
              <option value="350.99">350,99 zł</option>
              <option value="450.80">450,80 zł</option>
            </select>
          </div>

          <div className={styles.start}>
            <label htmlFor="startingPlace">Miejsce Startu:</label>
            <select
              name="startingPlace"
              id="startingPlace"
              value={flightForm.startingPlace}
              onChange={handleChange}
            >
              <option value="" hidden>
                Wybierz...
              </option>
              <option value="Jana Andersa 12, Wrocław">
                Jana Andersa 12, Wrocław
              </option>
              <option value="Mikołaja Reja 32, Gdańsk">
                Mikołaja Reja 32, Gdańsk
              </option>
              <option value="Tadeusza Nowaka 24, Wrocław">
                Tadeusza Nowaka 24, Wrocław
              </option>
              <option value="Augusta Zygmunta 3, Kraków">
                Augusta Zygmunta 3, Kraków
              </option>
              <option value="Mariana Krasica 34, Kraków">
                Mariana Krasica 34, Kraków
              </option>
            </select>
          </div>

          <div className={styles.priceVip}>
            <label htmlFor="ticketPriceVip">Cena biletu Vip:</label>
            <select
              name="ticketPriceVip"
              id="ticketPriceVip"
              value={flightForm.ticketPriceVip}
              onChange={handleChange}
            >
              <option value="" hidden>
                Wybierz...
              </option>
              <option value="400.34">450,34 zł</option>
              <option value="520.77">520,77 zł</option>
              <option value="618.56">618,56 zł</option>
              <option value="660.80">660,80 zł</option>
            </select>
          </div>

          <div className={styles.end}>
            <label htmlFor="landingPlace">Miejsce Lądowania:</label>
            <select
              name="landingPlace"
              id="landingPlace"
              value={flightForm.landingPlace}
              onChange={handleChange}
            >
              <option value="" hidden>
                Wybierz...
              </option>
              <option value="Jowisz">Jowisz</option>
              <option value="Mars">Mars</option>
              <option value="Księżyc">Księżyc</option>
            </select>
          </div>

          <div className={styles.places}>
            <label htmlFor="availabilityOfPlaces">
              Liczba dostępnych miejsc:
            </label>
            <select
              name="availabilityOfPlaces"
              id="availabilityOfPlaces"
              value={flightForm.availabilityOfPlaces}
              onChange={handleChange}
            >
              <option value="" hidden>
                Wybierz...
              </option>
              <option value="50">50</option>
            </select>
          </div>

          <div className={styles.submit}>
            <button
              type="submit"
              disabled={
                !flightForm.dateOfFlight ||
                !flightForm.ticketPrice ||
                !flightForm.startingPlace ||
                !flightForm.ticketPriceVip ||
                !flightForm.landingPlace ||
                !flightForm.availabilityOfPlaces
              }
              className={flightForm.invalid ? "disabledButton" : ""}
            >
              Zapisz
            </button>
          </div>
        </form>
      </div>
      <div className={styles.dashboard}>
        <div className={styles.header}>
          <div className={styles.left}>
            <span>Data</span>
            <span>Godzina</span>
            <span>Miejsce Startu</span>
            <span>Miejsce Lądowania</span>
          </div>
          <div className={styles.right}></div>
        </div>

        {flights.length === 0 ? (
          <div>{message}</div>
        ) : (
          flights.map((flight) => (
            <div className={styles.flight} key={flight.id}>
              <div className={styles.details}>
                <span>
                  {new Date(flight.dateOfFlight).toLocaleDateString()}
                </span>
                <span>
                  {new Date(flight.dateOfFlight).toLocaleTimeString()}
                </span>
                <span>{flight.startingPlace}</span>
                <span>{flight.landingPlace}</span>
              </div>
              <div className={styles.buttons}>
                <div className={styles.wrapper}>
                  <div className={styles.edit}>
                    <span>Edytuj</span>
                  </div>
                  <div className={styles.delete}>
                    <span onClick={() => deleteFlight(flight.id)}>Usuń</span>
                  </div>
                </div>
              </div>
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default AdminPage;
