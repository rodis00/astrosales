import React, { useEffect, useState } from "react";
import TransactionService from "../../services/transaction/TransactionService";
import Loader from "../loader/Loader";
import Ticket from "../ticket/Ticket";

const TicketsHistory = ({ userId }) => {
  const transactionService = new TransactionService();

  const [tickets, setTickets] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  const message = "Brak historii biletów";

  useEffect(() => {
    const getUserTicketsHistory = () => {
      setIsLoading(true);
      transactionService
        .getUserTransactionHistory(userId)
        .then((res) => {
          setTickets(res);
          setIsLoading(false);
        })
        .catch((err) => {
          setIsLoading(false);
          console.log(err);
        });
    };
    setTimeout(getUserTicketsHistory, 300);
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

export default TicketsHistory;
