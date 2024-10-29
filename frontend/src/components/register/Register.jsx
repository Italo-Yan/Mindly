import { NavLink } from "react-router-dom";
import styles from "./Register.module.css";

export function Register() {
  return (
    <div className={styles.container}>
      <section className={styles.registerContainer}>
        <h2 className={styles.title}>CADASTRO</h2>
        <div className={styles.registerContent}>
          <div className={styles.registerSection}>
            <h3 className={styles.subtitle}>ESPECIFICAÇÕES PACIENTE</h3>
            <NavLink className={styles.buttonContainer} to={"/patient"}>
              <button className={styles.button}>PACIENTE</button>
            </NavLink>
          </div>
          <div className={styles.separator}></div>
          <div className={styles.registerSection}>
            <h3 className={styles.subtitle}>ESPECIFICAÇÕES PROFISSIONAL</h3>
            <NavLink className={styles.buttonContainer} to={"/professional"}>
              <button className={styles.button}>PROFISSIONAL</button>
            </NavLink>
          </div>
        </div>
      </section>
    </div>
  )
}