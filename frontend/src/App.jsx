import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Header } from "./components/header/Header";
import { Footer } from "./components/footer/Footer";
import { Home } from "./pages/Home";
import "./global.css"

export function App() {

  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
      </Routes>
      <Footer />
    </Router>
  )
}
