import styles from "./schedule.module.css";

export function SchedulePatient() {
  return (
    <div className={styles.scheduleContainer}>
      <div className={styles.section}>
        <h2>CONSULTAS AGENDADAS</h2>
      </div>
      
      <div className={styles.section}>
        <div className={styles.calendar}>
          {/* Componente de calendário vai aqui */}
          <p>Calendário com Horários Disponíveis e Indisponíveis</p>
        </div>
      </div>
      
      <div className={styles.buttonContainer}>
        <button className={styles.button}>AGENDAR</button>
        <button className={styles.button}>DESMARCAR</button>
      </div>
    </div>
  );
}