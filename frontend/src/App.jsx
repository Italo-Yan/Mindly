import { Header } from "./components/header/Header";
import { Box } from "./components/box/Box";
import { Services } from "./components/services/Services";
import { About } from "./components/about/About";
import { Contact } from "./components/contact/Contact";
import { Footer } from "./components/footer/Footer";
import "./global.css"

export function App() {

  return (
    <div>
      <Header />

      <Box />

      <Services />

      <About />

      <Contact />

      <Footer />
    </div>
  )
}
