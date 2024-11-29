import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../components/auth-context/AuthContext";
import AuthService from "../../services/auth/AuthService";
import styles from "./RegisterPage.module.css";

const RegisterPage = () => {
  const authService = new AuthService();

  const [registerForm, setRegisterForm] = useState({ email: "", password: "" });
  const [formValid, setFormValid] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(registerForm);
    authService
      .register(registerForm)
      .then((res) => {
        if (res.status == 400) {
          setErrorMessage(res.detail);
          return;
        }
        alert("Konto utworzone pomyślnie.");
        login(res.token);
        console.log("success");
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
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
    setErrorMessage("");
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
        <p className={styles.errorBox}>{errorMessage}</p>
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
