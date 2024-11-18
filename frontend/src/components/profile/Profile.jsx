import { NavLink } from "react-router-dom";
import { useEffect, useState } from "react";

import { EditButton } from "../editButton/EditButton";
import { UserProfile } from "../user/UserProfile";
import { useAuth } from "../../Services/auth/useAuth";

import { getPacienteByEmail } from "../../Services/paciente/pacienteService";
import { getProfissionalByEmail } from "../../Services/profissional/profissionalService";

import styles from "./Profile.module.css";

export function Profile() {
  const { authData } = useAuth();
  const [objUser, setObjUser] = useState();
  const isProfessional = authData.role === "PROFISSIONAL";
  const isPatient = authData.role === "PACIENTE";
  const email = authData.email;

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
    return response;
  };

  const findProfissionalByEmail = async (email) => {
    const response = await getProfissionalByEmail(email);
    setObjUser(response.data);
    return response;
  };

  if (!objUser) {
    return <p>Carregando informações...</p>;
  }

  return (
    <div className={styles.profilePage}>
      <UserProfile
        name={objUser.nome || "Nome indisponível"}
        id={objUser.telefone || "Telefone indisponível"}
        crp={isProfessional ? objUser.crp : ""}
        description={isProfessional ? objUser.descricao : objUser.medicacao}
      />

      <div className={styles.cardContainer}>
        {/* Conteúdo específico para Profissionais */}
        {isProfessional && (
          <>
            <div className={`${styles.card} ${styles.professionalCard}`}>
              <h2>LISTA DE PACIENTES</h2>
              <NavLink to={"/listPacients"}>
                <button className={styles.viewButton}>VISUALIZAR</button>
              </NavLink>
              <h3>PACIENTES AGENDADOS</h3>
              <EditButton
                onClick={() => console.log("Editar Pacientes Agendados")}
              />
            </div>

            <div className={`${styles.card} ${styles.professionalCard}`}>
              <h2>AGENDA</h2>
              <div className={styles.calendar}>
                <h3>NOVEMBRO</h3>
                <p>Horários Disponíveis e Indisponíveis</p>
              </div>
              <NavLink to={"/schedule/professional"}>
                <EditButton onClick={() => console.log("Editar Agenda")} />
              </NavLink>
            </div>
          </>
        )}

        {/* Conteúdo específico para Pacientes */}
        {isPatient && (
          <div className={`${styles.card} ${styles.patientCard}`}>
            <h2>PRÓXIMAS CONSULTAS</h2>
            <div className={styles.appointments}>
              <p>Consulta com o Dr. João em 15 de novembro, às 10h</p>
              <p>Consulta com a Dra. Ana em 20 de novembro, às 14h</p>
            </div>
            <NavLink to={"/calendario"}>
              <button className={styles.viewButton}>
                AGENDAR NOVA CONSULTA
              </button>
            </NavLink>
          </div>
        )}
      </div>
    </div>
  );
}
