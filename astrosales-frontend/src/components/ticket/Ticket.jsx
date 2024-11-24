import "boxicons";
import React from "react";
import styles from "./Ticket.module.css";

const Ticket = ({ tickets, message }) => {
  const extractDateAndTime = (dateToFormat) => {
    const dateObj = new Date(dateToFormat);
    const date = dateObj
      .toLocaleDateString("pl-PL", {
        month: "2-digit",
        day: "2-digit",
        year: "numeric",
      })
      .replaceAll(".", "-");
    const time = dateObj.toLocaleTimeString("pl-PL", {
      hour: "2-digit",
      minute: "2-digit",
    });
    return { date, time };
  };

  if (tickets.length == 0) {
    return <h2 className={styles.noTickets}>{message}</h2>;
  }

  return (
    <>
      {tickets.map((ticket) => {
        const { date, time } = extractDateAndTime(ticket.dateOfFlight);
        return (
          <div key={ticket.id} className={styles.ticket}>
            <div className={styles.date}>
              <box-icon name="calendar" size="2rem"></box-icon>
              {date}
            </div>
            <div className={styles.time}>
              <box-icon name="time" size="2rem"></box-icon>
              {time}
            </div>
            <div className={styles.startPlace}>{ticket.startingPlace}</div>
            <div className={styles.endPlace}>{ticket.landingPlace}</div>
          </div>
        );
      })}
    </>
  );
};

export default Ticket;
