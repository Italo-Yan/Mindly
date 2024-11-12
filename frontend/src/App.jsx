import { BrowserRouter } from "react-router-dom";
import { Router } from './routes/Router';
import { AuthProvider } from './Services/auth/authProvider';

import "./global.css";

export function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Router />
      </BrowserRouter>
    </AuthProvider>
  );
}
