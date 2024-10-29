import { Descripton } from "../../components/description/Description";
import { Box } from "../../components/box/Box";
import { Services } from "../../components/services/Services";
import { About } from "../../components/about/About";
import { Contact } from "../../components/contact/Contact";

export function Home() {
  return (
    <div>
      <Descripton />
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