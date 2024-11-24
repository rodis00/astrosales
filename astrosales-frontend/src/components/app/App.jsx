import React from "react";
import { Outlet } from "react-router-dom";
import Header from "../header/Header";

const App = () => {
  return (
    <div>
      <Header />
      <div>
        <Outlet />
      </div>
    </div>
  );
};

export default App;
