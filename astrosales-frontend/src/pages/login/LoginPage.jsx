import React, { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../../components/auth-context/AuthContext";
import AuthService from "../../services/auth/AuthService";
import style from "./LoginPage.module.css";

const LoginPage = () => {
  const authService = new AuthService();
  const { login } = useContext(AuthContext);

  const [loginForm, setLoginForm] = useState({
    email: "",
    password: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (loginForm.email === "" || loginForm.password === "") {
      setErrorMessage("Email i hasło nie może być puste.");
    } else {
      setErrorMessage("");
      authService.login(loginForm).then((res) => {
        if (res.status === 404 || res.status === 400) {
          setErrorMessage(res.detail);
        } else {
          login(res.token);
          console.log("success");
          navigate("/");
        }
      });
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setLoginForm({ ...loginForm, [name]: value });
  };

  return (
    <div className={style.container}>
      <h1>Masz już konto?</h1>
      <h2>Zaloguj się!</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          name="email"
          value={loginForm.email}
          onChange={handleChange}
          placeholder="Adres email"
        />
        <input
          type="password"
          name="password"
          value={loginForm.password}
          onChange={handleChange}
          placeholder="Hasło"
        />
        {errorMessage && (
          <div className={style.errorBox}>
            <span>{errorMessage}</span>
          </div>
        )}
        <button type="submit">Zaloguj</button>
      </form>
      <div className={style.reminder}>
        <Link to={"#"}>Zapomniałeś hasła?</Link>
        <Link to={"/register"}>Zarejestruj</Link>
      </div>
    </div>
  );
};

export default LoginPage;
