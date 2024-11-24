import React from "react";
import styles from "./Loader.module.css";

const Loader = () => {
  return (
    <div className={styles.loaderDiv}>
      <box-icon className={styles.boxIcon} name="loader-circle"></box-icon>
    </div>
  );
};

export default Loader;
