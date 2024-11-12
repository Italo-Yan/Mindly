import { createContext, useContext, useState } from "react";
import PropTypes from "prop-types";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authData, setAuthData] = useState({ token: "", role: "" });

  const login = (token, role) => {
    setAuthData({ token, role });
  };

  const logout = () => {
    setAuthData({ token: "", role: "" });
  };

  return (
    <AuthContext.Provider value={{ authData, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

AuthProvider.propTypes = { children: PropTypes.node };

export const useAuth = () => useContext(AuthContext);