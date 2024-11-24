import React, { useState } from "react";
import { Link } from "react-router-dom";
import styles from "./RegisterPage.module.css";

const RegisterPage = () => {
  const [registerForm, setRegisterForm] = useState({ email: "", password: "" });
  const [formValid, setFormValid] = useState(false);

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(registerForm);
  };

  const validateForm = () => {
    if (registerForm.email !== "" && registerForm.password !== "") {
      setFormValid(true);
    } else {
      setFormValid(false);
    }
    console.log(formValid);
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setRegisterForm({ ...registerForm, [name]: value });
    validateForm();
  };

  return (
    <div className={styles.container}>
      <h1>Nie masz konta?</h1>
      <h2>Zarejestruj się!</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          value={registerForm.email}
          onChange={handleChange}
          placeholder="Adres email"
        />
        <input
          type="password"
          name="password"
          value={registerForm.password}
          onChange={handleChange}
          placeholder="Hasło"
        />
        <button
          type="submit"
          className={!formValid ? styles.disabledButton : ""}
        >
          Zarejestruj
        </button>
      </form>
      <div className={styles.reminder}>
        <Link to={"/login"}>Zaloguj</Link>
      </div>
    </div>
  );
};

export default RegisterPage;
