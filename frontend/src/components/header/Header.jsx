import { useEffect, useState } from "react";
import { NavLink, useNavigate } from "react-router-dom";

import { useAuth } from "../../Services/auth/useAuth";
import { getPacienteByEmail } from "../../Services/paciente/pacienteService";
import { getProfissionalByEmail } from "../../Services/profissional/profissionalService";

import defaultAvatar from '../../assets/PersonIcon.svg';
import styles from "./Header.module.css";

export function Header() {
  const { authData, logout } = useAuth();
  const [objUser, setObjUser] = useState(null);
  const navigate = useNavigate();

  const isProfessional = authData.role === "PROFISSIONAL";
  const isPatient = authData.role === "PACIENTE";
  const email = authData.email;

  const handleNavigation = (sectionId, event) => {
    if (event) event.preventDefault();
    navigate("/", { replace: true });
    setTimeout(() => {
      window.location.hash = sectionId;
    }, 50);
  };

  useEffect(() => {
    if (email && isProfessional) {
      findProfissionalByEmail(email);
    }
    if (email && isPatient) {
      findPacienteByEmail(email);
    }
  }, [email]);

  const findPacienteByEmail = async (email) => {
    const response = await getPacienteByEmail(email);
    setObjUser(response.data);
  };

  const findProfissionalByEmail = async (email) => {
    const response = await getProfissionalByEmail(email);
    setObjUser(response.data);
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <header className={styles.header}>
      <NavLink to="/">
        <h1>Mindly</h1>
      </NavLink>

      <ul className={styles.navMenu}>
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
        </li>        <li>
          <NavLink to="/explorer">Buscar Profissionais</NavLink>
        </li>
      </ul>

      <div className={styles.buttonContainer}>
        {authData.token ? (
          <div className={styles.profileContainer}>
            <img
              src={objUser?.profilePicture || defaultAvatar}
              alt="Perfil"
              className={styles.profileImage}
            />
            <span className={styles.userName}>{objUser?.nome || "Usuário"}</span>
            <div className={styles.dropdownMenu}>
              <button
                className={styles.dropdownItem}
                onClick={() => navigate("/perfil")}
              >
                Meu Perfil
              </button>
              <button className={styles.dropdownItem} onClick={handleLogout}>
                Sair
              </button>
            </div>
          </div>
        ) : (
          <>
            <NavLink to="/register">
              <button className={styles.button}>CADASTRE-SE</button>
            </NavLink>
            <NavLink to="/login">
              <button className={styles.button}>LOGIN</button>
            </NavLink>
          </>
        )}
      </div>
    </header>
  );
}
