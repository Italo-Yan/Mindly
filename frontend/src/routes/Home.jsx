import { Box } from "../components/box/Box";
import { Services } from "../components/services/Services";
import { About } from "../components/about/About";
import { Contact } from "../components/contact/Contact";
import { Cadastro } from "../components/cadastro/Cadastro";

export function Home() {
  return (
    <div>
      <section id="home">
        <Box />
      </section>
      <section id="services">
        <Services />
      </section>

      <section id="about">
        <About />
      </section>
      <section id="contact">
        <Contact />
      </section>
    </div>
  )
}	