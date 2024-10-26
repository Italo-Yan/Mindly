import styles from "./Register.module.css";

export function Register() {
  return (
    <section className={styles.registerContainer}>
      <h2 className={styles.title}>CADASTRO</h2>
      <div className={styles.registerContent}>
        <div className={styles.registerSection}>
          <h3 className={styles.subtitle}>ESPECIFICAÇÕES PACIENTE</h3>
          <button className={styles.button}>PACIENTE</button>
        </div>
        <div className={styles.separator}></div>
        <div className={styles.registerSection}>
          <h3 className={styles.subtitle}>ESPECIFICAÇÕES PROFISSIONAL</h3>
          <button className={styles.button}>PROFISSIONAL</button>
        </div>
      </div>
    </section>
  )
}