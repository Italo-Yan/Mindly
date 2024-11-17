import { useState } from "react";
import PropTypes from "prop-types";
import { AuthContext } from "./authContext";

export const AuthProvider = ({ children }) => {
  const [authData, setAuthData] = useState({ token: "", role: "", email: "" });

  const login = (token, role, email) => {
    setAuthData({ token, role, email });
  };

  const logout = () => {
    setAuthData({ token: "", role: "" , email: ""});
  };

  return (
    <AuthContext.Provider value={{ authData, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

AuthProvider.propTypes = {
  children: PropTypes.node,
};
