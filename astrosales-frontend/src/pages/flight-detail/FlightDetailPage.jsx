import { jwtDecode } from "jwt-decode";
import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import ErrorMessage from "../../components/error/ErrorMessage";
import Loader from "../../components/loader/Loader";
import NotFound from "../../components/not-found/NotFound";
import FlightService from "../../services/flight/FlightService";
import ReservationService from "../../services/reservation/ReservationService";
import TokenService from "../../services/token/TokenService";
import TransactionService from "../../services/transaction/TransactionService";
import style from "./FlightDetail.module.css";

const FlightDetailPage = () => {
  const flightService = new FlightService();
  const reservationService = new ReservationService();
  const tokenService = new TokenService();
  const transactionService = new TransactionService();

  const [isLoading, setIsLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState("");
  const { flightId } = useParams();
  const navigate = useNavigate();

  const [flight, setFlight] = useState(null);
  const [reservedPlaces, setReservedPlaces] = useState([]);
  const [choosenPlaces, setChoosenPlaces] = useState([]);

  useEffect(() => {
    const getFlightDetails = () => {
      flightService
        .getFlightById(flightId)
        .then((res) => {
          if (res.status === 404) {
            setErrorMessage(res.detail);
          } else {
            setFlight(res);
          }
        })
        .catch((err) => {
          console.log(err);
        })
        .finally(() => {
          setTimeout(() => {
            setIsLoading(false);
          }, 300);
        });
    };

    const getReservedPlaces = () => {
      reservationService
        .getReservedPlaces(flightId)
        .then((res) => {
          setReservedPlaces(res);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    setIsLoading(true);
    getFlightDetails();
    getReservedPlaces();
  }, [flightId]);

  const addPlace = (place) => {
    if (!reservedPlaces.includes(place)) {
      setChoosenPlaces((prevChoosenPlaces) =>
        prevChoosenPlaces.includes(place)
          ? prevChoosenPlaces.filter((p) => p !== place)
          : [...prevChoosenPlaces, place]
      );
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const reservations = choosenPlaces.map((place) => ({
      sector: place <= 20 ? "VIP" : "STANDARD",
      place,
    }));

    const transaction = {
      user: { id: jwtDecode(tokenService.getToken()).id },
      flight: { id: parseInt(flightId) },
      amountOfTickets: reservations.filter((p) => p.sector == "STANDARD")
        .length,
      amountOfTicketsVip: reservations.filter((p) => p.sector == "VIP").length,
      reservations,
    };

    transactionService
      .saveTransaction(transaction)
      .then((res) => {
        if (res.responseCode === "001") {
          alert(res.responseMessage);
          navigate("/account");
          return;
        }
        alert("Miejsca zostały zarezerwowane.");
        navigate("/account");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  function getRange(start, end) {
    return Array.from({ length: end - start + 1 }, (_, index) => start + index);
  }

  if (isLoading) {
    return <Loader />;
  }

  if (errorMessage) {
    return <NotFound message={errorMessage} />;
  }

  if (!flight) {
    return <ErrorMessage />;
  }

  return (
    <div className={style.container}>
      <div className={style.wrapper}>
        <div className={style.mainHeader}>
          <h1>Szczegóły podróży</h1>
        </div>
        <div className={style.mainSection}>
          <div className={style.left}>
            <div className={style.flightDetails}>
              <div className={style.detailsWrapper}>
                <div className={style.date}>
                  <div className={style.text}>
                    <span>Data:</span>
                  </div>
                  <div className={style.content}>
                    <span>
                      {new Date(flight.dateOfFlight).toLocaleDateString()}
                    </span>
                  </div>
                </div>
                <div className={style.time}>
                  <div className={style.text}>
                    <span>Godzina:</span>
                  </div>
                  <div className={style.content}>
                    <span>
                      {new Date(flight.dateOfFlight).toLocaleTimeString()}
                    </span>
                  </div>
                </div>
                <div className={style.startPlace}>
                  <div className={style.text}>
                    <span>Miejsce startu:</span>
                  </div>
                  <div className={style.content}>
                    <span>{flight.startingPlace}</span>
                  </div>
                </div>
                <div className={style.endPlace}>
                  <div className={style.text}>
                    <span>Miejsce lądowania:</span>
                  </div>
                  <div className={style.content}>
                    <span>{flight.landingPlace}</span>
                  </div>
                </div>
              </div>
            </div>
            <div className={style.purchaseDetails}>
              <div className={style.premiumTickets}>
                <div className={style.header}>
                  <span>Miejsca VIP</span>
                </div>
                <div className={style.vipSector}>
                  <div className={style.places}>
                    {getRange(1, 20).map((number) => (
                      <div
                        key={number}
                        value={number}
                        onClick={() => addPlace(number)}
                        className={`${style.place} ${
                          choosenPlaces.includes(number) ? style.selected : ""
                        } ${
                          reservedPlaces.includes(number) ? style.reserved : ""
                        }`}
                      >
                        {number}
                      </div>
                    ))}
                  </div>
                </div>
              </div>
              <div className={style.standardTickets}>
                <div className={style.header}>
                  <span>Miejsca Standard</span>
                </div>
                <div className={style.standardSector}>
                  <div className={style.places}>
                    {getRange(21, 50).map((number) => (
                      <div
                        key={number}
                        value={number}
                        onClick={() => addPlace(number)}
                        className={`${style.place} ${
                          choosenPlaces.includes(number) ? style.selected : ""
                        } ${
                          reservedPlaces.includes(number) ? style.reserved : ""
                        }`}
                      >
                        {number}
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className={style.right}>
            <div className={style.title}>
              <span>Wybrane miejsca</span>
            </div>
            <div className={style.placesContainer}>
              <div className={style.places}>
                {choosenPlaces.length === 0 ? (
                  <span>Brak wybranych miejsc.</span>
                ) : (
                  <>
                    <div className={style.choosenPlace}>
                      {choosenPlaces.map((place) => (
                        <div key={place} className={style.place}>
                          {place}
                        </div>
                      ))}
                    </div>
                    <div className={style.continueButton}>
                      <Link onClick={handleSubmit} to={"#"}>
                        Wybierz
                      </Link>
                    </div>
                  </>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className={style.description}>
        <span>
          <b>VIP</b> - bilet w górnej części promu. Zapewnia posiłki wliczone w
          cenę oraz dostęp do muzyki umilającej podróż.
        </span>
        <span>
          <b>Standard</b> - bilet w dolnej części promu. Posiłki oraz dostęp do
          muzyki nie jest wliczony w koszt biletu.
        </span>
      </div>
    </div>
  );
};

export default FlightDetailPage;
