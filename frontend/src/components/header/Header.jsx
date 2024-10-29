import { NavLink, useNavigate } from "react-router-dom";
import styles from "./Header.module.css";

export function Header() {
  const navigate = useNavigate();

  const handleNavigation = (sectionId, event) => {
    if (event) event.preventDefault();

    navigate("/", { replace: true });
    setTimeout(() => {
      window.location.hash = sectionId;
    }, 50);
  };

  return (
    <>
      <header className={styles.header}>
        <NavLink to="/">
          <h1>Mindly</h1>
        </NavLink>

        <ul>
          <li>
            <a href="#home" onClick={(event) => handleNavigation("home", event)}>
              Início
            </a>
          </li>
          <li>
            <a href="#about" onClick={(event) => handleNavigation("about", event)}>
              Sobre
            </a>
          </li>
          <li>
            <a href="#services" onClick={(event) => handleNavigation("services", event)}>
              Serviços
            </a>
          </li>
          <li>
            <a href="#contact" onClick={(event) => handleNavigation("contact", event)}>
              Contato
            </a>
          </li>
          <li>
            <NavLink to="/explorer">
              Buscar Profissionais
            </NavLink>
          </li>
        </ul>

        <div className={styles.buttonContainer}>
          <NavLink to="/register">
            <button className={styles.button} aria-label="Cadastre-se">CADASTRE-SE</button>
          </NavLink>
          <NavLink to="/login">
            <button className={styles.button} aria-label="Login">LOGIN</button>
          </NavLink>
        </div>
      </header>
    </>
  );
}
