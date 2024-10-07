import styles from "./Box.module.css";

export function Box() {
  return (
    <div className={styles.infoBox}>
      <div className={styles.infoItem}>
        <h2>+3.500</h2>
        <p>Pacientes atendidos</p>
      </div>

      <div className={styles.divider}></div>

      <div className={styles.infoItem}>
        <h2>+15</h2>
        <p>Especialistas disponíveis</p>
      </div>

      <div className={styles.divider}></div>

      <div className={styles.infoItem}>
        <h2>+10</h2>
        <p>Anos no mercado</p>
      </div>

    </div>
  )
}