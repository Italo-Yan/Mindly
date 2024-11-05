import { NavLink } from "react-router-dom";

import { EditButton } from "../editButton/EditButton";
import { UserProfile } from "../user/UserProfile";

import styles from "./Profile.module.css";

export function Profile() {
  return (
    <div className={styles.profilePage}>
      <UserProfile
        name="Maria Oliveira"
        id="75123456789"
        crp="CRP 1234/04"
        description="Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco "
      />
      <div className={styles.cardContainer}>
        <div className={styles.card}>
          <h2>LISTA DE PACIENTES</h2>
          <NavLink to={"/listPacients"}>
            <button className={styles.viewButton}>VISUALIZAR</button>
          </NavLink>
          <h3>PACIENTES AGENDADOS</h3>
          <EditButton onClick={() => console.log("Editar Pacientes Agendados")} />
        </div>
        <div className={styles.card}>
          <h2>AGENDA</h2>
          <div className={styles.calendar}>
            <h3>NOVEMBRO</h3>
            {/* CALENDARIO */}
            <p>Horários Disponíveis e Indisponíveis</p>
          </div>
          <EditButton onClick={() => console.log("Editar Agenda")} />
        </div>
      </div>
    </div>
  )
}