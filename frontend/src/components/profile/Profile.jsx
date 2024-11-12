import { NavLink } from "react-router-dom";

import { EditButton } from "../editButton/EditButton";
import { UserProfile } from "../user/UserProfile";
import { useAuth } from "../../Services/auth/useAuth";

import styles from "./Profile.module.css";

export function Profile() {
  const { authData } = useAuth();
  const isProfessional = authData.role === "PROFISSIONAL";

  return (
    <div className={styles.profilePage}>
      <UserProfile
        name="Maria Oliveira"
        id="75123456789"
        crp={isProfessional ? "CRP 1234/04" : ""}
        description="Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim."
      />

      <div className={styles.cardContainer}>
        {/* Conteúdo específico para Profissionais */}
        {isProfessional ? (
          <>
            <div className={`${styles.card} ${styles.professionalCard}`}>
              <h2>LISTA DE PACIENTES</h2>
              <NavLink to={"/listPacients"}>
                <button className={styles.viewButton}>VISUALIZAR</button>
              </NavLink>
              <h3>PACIENTES AGENDADOS</h3>
              <EditButton onClick={() => console.log("Editar Pacientes Agendados")} />
            </div>

            <div className={`${styles.card} ${styles.professionalCard}`}>
              <h2>AGENDA</h2>
              <div className={styles.calendar}>
                <h3>NOVEMBRO</h3>
                <p>Horários Disponíveis e Indisponíveis</p>
              </div>
              <EditButton onClick={() => console.log("Editar Agenda")} />
            </div>
          </>
        ) : (
          /* Conteúdo específico para Pacientes */
          <div className={`${styles.card} ${styles.patientCard}`}>
            <h2>PRÓXIMAS CONSULTAS</h2>
            <div className={styles.appointments}>
              <p>Consulta com o Dr. João em 15 de novembro, às 10h</p>
              <p>Consulta com a Dra. Ana em 20 de novembro, às 14h</p>
            </div>
            <NavLink to={"/bookAppointment"}>
              <button className={styles.viewButton}>AGENDAR NOVA CONSULTA</button>
            </NavLink>
          </div>
        )}
      </div>
    </div>
  );
}