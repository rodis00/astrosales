import React, { useEffect, useState } from "react";
import TransactionService from "../../services/transaction/TransactionService";
import Loader from "../loader/Loader";
import Ticket from "../ticket/Ticket";

const ActiveTickets = ({ userId }) => {
  const transactionService = new TransactionService();

  const [tickets, setTickets] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const message = "Brak aktywnych biletów";

  useEffect(() => {
    setIsLoading(true);
    const getUserActiveTickets = () => {
      transactionService
        .getUserActiveTransactions(userId)
        .then((res) => {
          setTickets(res);
          setIsLoading(false);
        })
        .catch((err) => {
          console.log(err);
          setIsLoading(false);
        });
    };

    setTimeout(getUserActiveTickets, 300);
  }, [userId]);

  if (isLoading) {
    return <Loader />;
  }

  return (
    <div className="container">
      <Ticket tickets={tickets} message={message} />
    </div>
  );
};

export default ActiveTickets;
