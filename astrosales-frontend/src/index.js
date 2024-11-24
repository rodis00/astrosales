import React from "react";
import ReactDOM from "react-dom/client";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./components/app/App";
import AuthProvider from "./components/auth-context/AuthContext";
import ProtectedRoute from "./components/auth-context/ProtectedRoute";
import Header from "./components/header/Header";
import "./index.css";
import AccountPage from "./pages/account/AccountPage";
import AdminPage from "./pages/admin/AdminPage";
import FlightDetailPage from "./pages/flight-detail/FlightDetailPage";
import FlightPage from "./pages/flight/FlightPage";
import HomePage from "./pages/home/HomePage";
import LoginPage from "./pages/login/LoginPage";
import RegisterPage from "./pages/register/RegisterPage";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(document.getElementById("root"));
const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <HomePage />,
      },
      {
        path: "/flights",
        element: <FlightPage />,
      },
      {
        path: "/flights/:flightId",
        element: <FlightDetailPage />,
      },
      {
        path: "/login",
        element: <LoginPage />,
      },
      {
        path: "/register",
        element: <RegisterPage />,
      },
      {
        path: "/account",
        element: (
          <ProtectedRoute>
            <AccountPage />
          </ProtectedRoute>
        ),
      },
      {
        path: "/admin",
        element: (
          <ProtectedRoute adminOnly={true}>
            <AdminPage />
          </ProtectedRoute>
        ),
      },
    ],
  },
]);
root.render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router}>
        <Header />
      </RouterProvider>
    </AuthProvider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
