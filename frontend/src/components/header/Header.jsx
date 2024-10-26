import { NavLink } from "react-router-dom";
import styles from "./Header.module.css";

export function Header() {
  return (
    <>
      <header className={styles.header}>
        <NavLink to={"/"}>
          <h1>Mindly</h1>
        </NavLink>

        <ul>
          <li><a href="#home">Início</a></li>
          <li><a href="#about">Sobre</a></li>
          <li><a href="#services">Serviços</a></li>
          <li><a href="#contact">Contato</a></li>
          <li><a href="#search">Buscar Profissionais</a></li>
        </ul>

        <div>
          <NavLink className={styles.buttonContainer} to={"/register"}>
            <button aria-label="Cadastre-se">CADASTRE-SE</button>
            <button aria-label="Cadastre-se">LOGIN</button>
          </NavLink>
        </div>
      </header>

      <main className={styles.text}>
        <p>Receba as nossas boas-vindas</p>
        <h1>
          Organização, agendamento e contato! <br />
          Tudo em um só lugar.
        </h1>

        <button>SAIBA MAIS</button>

      </main>
    </>
  );
}
