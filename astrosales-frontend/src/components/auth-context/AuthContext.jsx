import React, { createContext, useEffect, useState } from "react";
import AuthService from "../../services/auth/AuthService";
import TokenService from "../../services/token/TokenService";

export const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const tokenService = new TokenService();
  const authService = new AuthService();

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [isAuthChecked, setIsAuthChecked] = useState(false);

  useEffect(() => {
    const initializeAuth = async () => {
      const token = tokenService.getToken();
      if (token) {
        setIsLoggedIn(true);
        checkIfUserIsAdmin();
      } else {
        setIsLoggedIn(false);
        setIsAdmin(false);
      }
      setIsAuthChecked(true);
    };

    initializeAuth();
  }, []);

  const login = async (token) => {
    tokenService.setToken(token);
    setIsLoggedIn(true);
    checkIfUserIsAdmin();
  };

  const checkIfUserIsAdmin = async () => {
    const data = await authService.getAuthorities();
    setIsAdmin(data.includes("ROLE_ADMIN"));
  };

  const logout = () => {
    tokenService.removeToken();
    setIsLoggedIn(false);
    setIsAdmin(false);
  };

  return (
    <AuthContext.Provider
      value={{ isLoggedIn, isAdmin, isAuthChecked, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
