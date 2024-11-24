import React from "react";
import styles from "./NotFound.module.css";

const NotFound = ({ message }) => {
  return (
    <div className={styles.errorMessage}>
      <box-icon name="error" color="orange"></box-icon>
      <span>{message}</span>
    </div>
  );
};

export default NotFound;
