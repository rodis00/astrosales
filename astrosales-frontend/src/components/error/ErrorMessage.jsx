import React from "react";
import styles from "./ErrorMessage.module.css";

const ErrorMessage = () => {
  return (
    <div className={styles.errorMessage}>
      <box-icon name="no-signal" color="red"></box-icon>
      <span>
        Wystąpił problem połączenia z serwerem. Sprawdź swoje połączenie
        internetowe i spróbuj ponownie.
      </span>
    </div>
  );
};

export default ErrorMessage;
