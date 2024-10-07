import { Header } from "./components/header/Header";
import { Box } from "./components/box/Box";
import { Services } from "./components/services/Services";

// import styles from "./App.module.css";

import "./global.css"

export function App() {

  return (
    <div>
      <Header />

      <Box />

      <Services />
    </div>
  )
}
