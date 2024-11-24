import { useContext } from "react";
import { Navigate } from "react-router-dom";
import Loader from "../../components/loader/Loader";
import { AuthContext } from "./AuthContext";

const ProtectedRoute = ({ children, adminOnly = false }) => {
  const { isLoggedIn, isAdmin, isAuthChecked } = useContext(AuthContext);

  if (!isAuthChecked) {
    return <Loader />;
  }

  if (!isLoggedIn) {
    return <Navigate to="/login" />;
  }

  if (adminOnly && !isAdmin) {
    return <Navigate to="/" />;
  }

  return children;
};

export default ProtectedRoute;
