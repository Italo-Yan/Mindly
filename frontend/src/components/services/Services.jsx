import { CheckCircle } from "@phosphor-icons/react";
import styles from "./Services.module.css";

export function Services() {
  return (
    <section className={styles.services}>
      <h3>SERVIÇOS</h3>
      <h2>O que oferecemos?</h2>

      <div className={styles.servicesGrid}>
        <div className={styles.serviceCard}>
          <CheckCircle size={24} className={styles.check}/>
          <h4>Consultas</h4>
          <p>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.</p>
        </div>
      </div>

      <div className={styles.servicesGrid}>
        <div className={styles.serviceCard}>
          <CheckCircle size={24} className={styles.check}/>
          <h4>Grade de horários</h4>
          <p>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.</p>
        </div>
      </div>

    </section>
  )
}